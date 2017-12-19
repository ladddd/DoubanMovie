package com.ladddd.douban.model

import com.ladddd.baselib.network.RetrofitProvider
import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.model.api.HttpAdressConfig
import com.ladddd.douban.model.api.MovieApi
import io.reactivex.Observable

/**
 * Created by 陈伟达 on 2017/12/7.
 *
 */
object MovieRepository {

    fun getTop250(start:Int = 1, count:Int = 25) : Observable<MovieListData> {
        return RetrofitProvider.getRetrofit(HttpAdressConfig.BASE_URL)
                .create(MovieApi::class.java)
                .getTop250(start, count)
    }
}