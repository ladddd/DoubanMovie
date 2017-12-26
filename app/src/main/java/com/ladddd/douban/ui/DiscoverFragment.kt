package com.ladddd.douban.ui

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import com.ladddd.baselib.componet.BaseFragment
import com.ladddd.baselib.ptr.DoubanProgressView
import com.ladddd.douban.R
import org.jetbrains.anko.support.v4.find

/**
 * Created by 陈伟达 on 2017/12/21.
 */
class DiscoverFragment : BaseFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_discover
    }

    override fun initViews() {
        val dpv = find<DoubanProgressView>(R.id.dpv)
        val animator = ObjectAnimator.ofFloat(dpv, "progress", 0f, 360f)
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000
//        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }
}