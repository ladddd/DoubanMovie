package com.ladddd.douban

import android.app.Application
import com.facebook.stetho.Stetho
import com.ladddd.baselib.ptr.DoubanHeader
import com.ladddd.baselib.utils.Utils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * Created by 陈伟达 on 2017/12/6.
 */

class DoubanApplication : Application() {

    init {
        //全局指定ptr header footer
        SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
//            ClassicsHeader(context)
            DoubanHeader(context)
        }
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Utils.init(this)
    }
}
