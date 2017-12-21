package com.ladddd.baselib.ptr

import `in`.srain.cube.views.ptr.PtrFrameLayout
import android.content.Context
import android.util.AttributeSet

/**
 * Created by 陈伟达 on 2017/12/21.
 */
class DoubanPtrLayout(context: Context, attributeSet: AttributeSet? = null,
                      defStyle: Int = 0) : PtrFrameLayout(context, attributeSet, defStyle) {

    companion object {
        private val LOADING_MIN_TIME = 500
        private val DURATION_TO_CLOSE_HEADER = 1000
    }

    init {
        headerView = DoubanPtrHeader(context)
        addPtrUIHandler(headerView as DoubanPtrHeader)

        setLoadingMinTime(LOADING_MIN_TIME)
        setDurationToClose(DURATION_TO_CLOSE_HEADER)
        disableWhenHorizontalMove(true)
    }
}