package com.ladddd.baselib.utils

import java.io.File

/**
 * Created by 陈伟达 on 2017/12/6.
 */
object CacheUtils {

    private val HTTP_CACHE_DIR = "okhttp"

    //获取缓存目录
    //获取网络缓存目录
    @Throws(RuntimeException::class)
    fun getHttpCacheDir() : File {
        val dir = File(Utils.app?.cacheDir, HTTP_CACHE_DIR)
        if (!dir.exists() && !dir.mkdirs()) {
            throw RuntimeException("can't make dirs in " + dir.absolutePath)
        }

        return dir
    }

    //读写文件缓存
}