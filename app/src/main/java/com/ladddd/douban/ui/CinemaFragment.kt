package com.ladddd.douban.ui

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.ladddd.baselib.componet.BaseFragment
import com.ladddd.douban.R
import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.network.RetrofitProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast

/**
 * Created by 陈伟达 on 2017/12/20.
 */
class CinemaFragment : BaseFragment() {

    private lateinit var list_cinema : RecyclerView
    private var pageNum = 0
    private var pageSize = 20

    override fun getLayoutResId(): Int {
        return R.layout.fragment_cinema
    }

    override fun initViews() {
        list_cinema = find(R.id.list_cinema)
        list_cinema.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        loadData()
    }

    @SuppressLint("CheckResult")
    private fun loadData() {
        RetrofitProvider.getDoubanService()
                .getTop250(pageNum, pageSize)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieListData: MovieListData? -> toast(movieListData.toString())
                }, {

                }, {

                })
    }
}