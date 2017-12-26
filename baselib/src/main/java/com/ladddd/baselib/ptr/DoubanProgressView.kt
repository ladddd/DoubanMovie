package com.ladddd.baselib.ptr

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ladddd.baselib.R

/**
 * Created by 陈伟达 on 2017/12/25.
 */
class DoubanProgressView(context: Context, attributeSet: AttributeSet? = null) : View(context, attributeSet) {

    private val rect: RectF = RectF()

    private val mPaint: Paint = Paint()

    var progress:Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    var color:Int = Color.BLACK

    private var invalidate:Boolean


    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.DoubanProgressView)
        color = typedArray.getColor(R.styleable.DoubanProgressView_progressColor, Color.BLACK)
        invalidate = typedArray.getBoolean(R.styleable.DoubanProgressView_invalidate, false)
        typedArray.recycle()

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val radius = Math.min(width.toFloat(), height.toFloat())
        mPaint.color = color
        mPaint.strokeWidth = radius/6

        rect.set(mPaint.strokeWidth/2, mPaint.strokeWidth/2,
                radius - mPaint.strokeWidth/2, radius - mPaint.strokeWidth/2)

        canvas?.drawArc(rect, 30 + progress, getSwipeRadius(), false, mPaint)
        canvas?.drawArc(rect, 225 + progress, 1f, false, mPaint)
        canvas?.drawArc(rect, 315 + progress, 1f, false, mPaint)
    }

    private fun getSwipeRadius() : Float {
        return if(progress < 180)
            120 + progress * 165 / 180
        else
            285 - (progress - 180) * 165 / 180
    }

//    fun animateToProgress(targetRaw: Float) : Int {
//        val animator = ValueAnimator.ofFloat(progress, targetRaw)
//        animator.duration = (Math.abs(targetRaw - progress) / 360 * 1000).toLong()
//        animator.addUpdateListener {
//            animator1 -> progress = animator1.animatedValue as Float
//        }
//        animator.start()
//        return animator.duration.toInt()
//    }
}