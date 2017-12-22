package com.ladddd.douban.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.ladddd.baselib.componet.BaseFragment
import com.ladddd.douban.R
import com.ladddd.douban.adapter.MovieAdapter
import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.bean.MovieSubject
import com.ladddd.douban.network.RetrofitProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.support.v4.find

/**
 * Created by 陈伟达 on 2017/12/20.
 */
@SuppressLint("CheckResult")
class CinemaFragment : BaseFragment() {

    private lateinit var list_cinema : RecyclerView
    private lateinit var adapter : MovieAdapter
    private var pageNum = 0
    private val pageSize = 25

    override fun getLayoutResId(): Int {
        return R.layout.fragment_cinema
    }

    override fun initViews() {
        list_cinema = find(R.id.list_cinema)
        list_cinema.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = MovieAdapter()
        list_cinema.adapter = adapter

        adapter.setOnLoadMoreListener({
            pageNum += pageSize
            loadData()
        }, list_cinema)

        loadData()
    }

    private fun loadData() {
        RetrofitProvider.getDoubanService()
                .getTop250(pageNum, pageSize)
                .compose(bindToLifecycle())
                .map { movieListData: MovieListData -> movieListData.subjects }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    subjects: List<MovieSubject> ->
                        if (pageNum == 0)
                            adapter.setNewData(subjects)
                        else
                            adapter.addData(subjects)
                        if (pageNum == 250)
                            adapter.loadMoreEnd()
                        else
                            adapter.loadMoreComplete()

                }, {
                    if (pageNum == 0)
                        adapter.loadMoreFail()
                })
    }
}