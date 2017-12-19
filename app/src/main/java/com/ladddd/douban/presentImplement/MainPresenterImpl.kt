package com.ladddd.douban.presentImplement

import android.widget.Toast
import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.interactor.MainInteractor
import com.ladddd.douban.interactorInplement.MainInteractorImpl
import com.ladddd.douban.presenter.MainPresenter
import com.ladddd.douban.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 陈伟达 on 2017/12/6.
 */
class MainPresenterImpl(val mainView: MainView) : MainPresenter {

    private val mainInteractor : MainInteractor = MainInteractorImpl()

    override fun init() {
        mainInteractor.getTop250()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieListData: MovieListData? ->
                    val subjects = movieListData?.subjects
                    mainView.showTop250(subjects)
                }, {
                    throwable: Throwable? ->
                    mainView.showTop250Failed(throwable?.message)
                }, {

                })
    }
}