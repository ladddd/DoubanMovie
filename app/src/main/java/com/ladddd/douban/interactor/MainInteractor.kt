package com.ladddd.douban.interactor

import com.ladddd.douban.bean.MovieListData
import io.reactivex.Observable
import java.util.*

/**
 * Created by 陈伟达 on 2017/12/6.
 *
 * model & controller
 */
interface MainInteractor {

    fun getTop250(start:Int = 0, count:Int = 25) : Observable<MovieListData>
}