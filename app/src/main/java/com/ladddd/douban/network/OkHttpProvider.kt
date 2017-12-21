package com.ladddd.douban.network

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.ladddd.baselib.network.interceptor.CacheInterceptor
import com.ladddd.baselib.network.interceptor.SignInterceptor
import com.ladddd.baselib.utils.CacheUtils
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Created by 陈伟达 on 2017/12/4.
 */
object OkHttpProvider {
    private val TAG = "OkHttpProvider"
    private val LOG_TAG = "okhttp"
    var okHttpClient : OkHttpClient

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            message -> Log.d(LOG_TAG, message)
        })
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cache = Cache(CacheUtils.getHttpCacheDir(), 1024 * 1024 * 100)

        Log.d(TAG, "init start")
        okHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .addInterceptor(logInterceptor)
                .addInterceptor(SignInterceptor())
                .addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(CacheInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

    }
}