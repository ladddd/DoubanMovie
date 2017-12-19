package com.ladddd.baselib.component

import android.app.Application
import com.facebook.stetho.Stetho
import com.ladddd.baselib.utils.Utils

/**
 * Created by 陈伟达 on 2017/12/6.
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        Utils.init(this)
    }
}