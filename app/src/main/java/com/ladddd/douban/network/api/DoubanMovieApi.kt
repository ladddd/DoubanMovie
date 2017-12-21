package com.ladddd.douban.model.api

import com.ladddd.douban.bean.MovieListData
import com.ladddd.douban.bean.MovieSubject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by 陈伟达 on 2017/12/7.
 */
interface DoubanMovieApi {

    @GET("/v2/movie/top250")
    fun getTop250(@Query("start") start:Int?, @Query("count") count:Int?) : Observable<MovieListData>

    @GET("/v2/movie/subject/{id}")
    fun getMovieDetail(@Path("id") id:Int?) : Observable<MovieSubject>
}