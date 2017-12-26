package com.ladddd.baselib.ptr

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.ladddd.baselib.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshKernel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import org.jetbrains.anko.find

/**
 * Created by 陈伟达 on 2017/12/25.
 *
 * custom smart refresh header
 */
class DoubanHeader(context: Context, attributeSet: AttributeSet? = null) : RelativeLayout(context, attributeSet), RefreshHeader {

    private var dpv : DoubanProgressView

    private var mIsFinished : Boolean = false
    private var animator : ValueAnimator
    private var mBackgroundColor = Color.TRANSPARENT
    private var mRefreshKernel : RefreshKernel? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.douban_header, this)
        dpv = find(R.id.dpv)
        animator = ValueAnimator.ofFloat(0f, 360f)
    }

    override fun onInitialized(kernel: RefreshKernel?, height: Int, extendHeight: Int) {
        mRefreshKernel = kernel
        mRefreshKernel?.requestDrawBackgoundForHeader(mBackgroundColor)
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun onPullingDown(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {

    }

    override fun onReleasing(percent: Float, offset: Int, headerHeight: Int, extendHeight: Int) {

    }

    override fun onStartAnimator(layout: RefreshLayout?, height: Int, extendHeight: Int) {
        mIsFinished = false
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 1000
        animator.addUpdateListener {
            animator ->
                dpv.progress = animator.animatedValue as Float
        }
        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationEnd(p0: Animator?) {}

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {
                if (mIsFinished) {
                    animator.removeAllUpdateListeners()
                    animator.removeAllListeners()
                    animator.cancel()
                }
            }
        })

        animator.start()
    }

    override fun onFinish(layout: RefreshLayout?, success: Boolean): Int {
        mIsFinished = true
        return (Math.abs(360 - dpv.progress) / 360 * 1000 + 500).toInt()
    }

    override fun onStateChanged(refreshLayout: RefreshLayout?, oldState: RefreshState?, newState: RefreshState?) {
        //TODO
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun getView(): View {
        return this
    }

    override fun setPrimaryColors(vararg colors: Int) {
        if (colors.size == 1)
            dpv.color = colors[0]
        else if (colors.size > 1) {
            dpv.color = colors[0]
            setPrimaryColor(colors[1])
        }
    }

    private fun setPrimaryColor(@ColorInt primaryColor: Int) {
        mBackgroundColor = primaryColor
        setBackgroundColor(mBackgroundColor)
    }
}