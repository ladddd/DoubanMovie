package com.ladddd.baselib.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils
import android.util.Log

/**
 * Created by 陈伟达 on 2017/12/19.
 */
object NetworkUtils {

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private fun getNetworkInfo() : NetworkInfo? {
        val cm = Utils.app?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    /**
     * 判断网络是否连接
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    fun isConnected() : Boolean {
        val networkInfo = getNetworkInfo()
        return networkInfo?.isConnected ?: false
    }

    /**
     * 判断网络是否可用
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET"/>}</p>
     * <p>需要异步 ping，如果 ping 不通就说明网络不可用</p>
     *
     * @param ip ip 地址（自己服务器 ip），如果为空，ip 为阿里巴巴公共 ip
     * @return {@code true}: 可用<br>{@code false}: 不可用
     */
    fun isAvailabelByPing(ip:String? = "223.5.5.5") : Boolean {
        val commandResult = ShellUtils.execCmd(String.format("ping -c 1 %s", ip))
        if (TextUtils.isEmpty(commandResult.successMsg)) Log.d("NetworkUtils", commandResult.successMsg)
        if (TextUtils.isEmpty(commandResult.errorMsg)) Log.d("NetworkUtils", commandResult.errorMsg)
        return commandResult.result == 0
    }
}