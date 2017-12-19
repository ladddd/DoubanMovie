package com.ladddd.douban.view

import com.ladddd.douban.bean.MovieSubject

/**
 * Created by 陈伟达 on 2017/12/6.
 */
interface MainView {

    fun showTop250(subjects:List<MovieSubject>?)

    fun showTop250Failed(message:String?)
}