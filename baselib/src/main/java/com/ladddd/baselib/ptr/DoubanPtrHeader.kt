package com.ladddd.baselib.ptr

import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrUIHandler
import `in`.srain.cube.views.ptr.indicator.PtrIndicator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import com.ladddd.baselib.R
import org.jetbrains.anko.find

/**
 * Created by 陈伟达 on 2017/12/21.
 */
class DoubanPtrHeader(context: Context) : RelativeLayout(context), PtrUIHandler{

    private val iv_loading : ImageView
    private val rotateAnimation : ObjectAnimator

    init {
        val contentView = LayoutInflater.from(context).inflate(R.layout.douban_ptr_header, this)
        iv_loading = contentView.find(R.id.iv_loading)

        rotateAnimation = ObjectAnimator.ofFloat(iv_loading, "rotation", 0f, 360f)
        rotateAnimation.duration = 500
        rotateAnimation.repeatCount = ValueAnimator.INFINITE
        rotateAnimation.repeatMode = ValueAnimator.RESTART
    }

    override fun onUIReset(frame: PtrFrameLayout?) {

    }

    override fun onUIRefreshPrepare(frame: PtrFrameLayout?) {

    }

    override fun onUIRefreshBegin(frame: PtrFrameLayout?) {
        rotateAnimation.start()
    }

    override fun onUIPositionChange(frame: PtrFrameLayout?, isUnderTouch: Boolean, status: Byte, ptrIndicator: PtrIndicator?) {

    }

    override fun onUIRefreshComplete(frame: PtrFrameLayout?) {
        rotateAnimation.end()
    }
}