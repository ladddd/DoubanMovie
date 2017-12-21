package com.ladddd.baselib.network.interceptor

import com.ladddd.baselib.utils.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by 陈伟达 on 2017/12/19.
 * 当server不支持Cache_Control时，根据网络状况判断是否采用缓存
 */
class CacheInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = if (NetworkUtils.isAvailabelByPing()) {
            request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build()
        } else {
            request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
        }

        return chain.proceed(request)
    }
}