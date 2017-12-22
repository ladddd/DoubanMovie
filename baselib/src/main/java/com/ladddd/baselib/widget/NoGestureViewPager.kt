package com.ladddd.baselib.widget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by 陈伟达 on 2017/12/22.
 */
class NoGestureViewPager(context: Context, attributeSet: AttributeSet? = null) : ViewPager(context, attributeSet) {

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}