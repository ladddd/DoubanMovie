package com.ladddd.douban.interactorInplement

import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.interactor.MainInteractor
import com.ladddd.douban.model.MovieRepository
import io.reactivex.Observable

/**
 * Created by 陈伟达 on 2017/12/6.
 */
class MainInteractorImpl : MainInteractor {

    override fun getTop250(start: Int, count: Int): Observable<MovieListData> {
        return MovieRepository.getTop250(start, count)
    }
}