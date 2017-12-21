package com.ladddd.baselib.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.*

/**
 * Created by 陈伟达 on 2017/12/6.
 */
object Utils{

    var app: Application? = null
        @Throws(NullPointerException::class)
        get() {
            return field ?: throw NullPointerException("u should init first")
        }
        private set

    var sTopActivityWeakRef : WeakReference<Activity>? = null

    private val list: MutableList<Activity> = LinkedList()

    fun init(application: Application) {
        app = application
        //safe call
        app?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let {
                    list.add(p0)
                    setTopActivityWeakRef(p0)
                }
            }

            override fun onActivityStarted(p0: Activity?) {
                //elvis操作符，提供p0如果为空的分支
                setTopActivityWeakRef(p0 ?: return)
            }

            override fun onActivityPaused(p0: Activity?) {
                setTopActivityWeakRef(p0 ?: return)
            }

            //kotlin的接口可以带默认实现，不需要实现optional的接口。但是java接口必须全部实现
            override fun onActivityResumed(p0: Activity?) {

            }

            override fun onActivityDestroyed(p0: Activity?) {

            }

            override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

            }

            override fun onActivityStopped(p0: Activity?) {

            }
        })
    }

    private fun setTopActivityWeakRef(activity: Activity) {
        if (activity != sTopActivityWeakRef?.get()) {
            sTopActivityWeakRef = WeakReference(activity)
        }
    }
}