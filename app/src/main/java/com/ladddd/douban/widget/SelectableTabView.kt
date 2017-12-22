package com.ladddd.douban.widget

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.ladddd.douban.R
import org.jetbrains.anko.find
import org.jetbrains.anko.textColor

/**
 * Created by 陈伟达 on 2017/6/23.
 */

class SelectableTabView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    companion object {
        private val ANIMATION_DURATION = 300L
        private val UNREAD_MAX_TEXT = "99+"
    }

    var tabSelected = false
        set(value) {
            if (field != value) {
                field = value
                handleTabSelectChanged()
            }
        }
    var unreadCount = 0
        set(value) {
            field = value
            showUnread()
        }
    var hasNotice = false
        set(value) {
            field = value
            showNotice()
        }

    private val tv_tab_text: TextView
    private val iv_tab_icon: ImageView
    private val tv_unread: TextView
    private val iv_notice: ImageView

    private val normalDrawableResId: Int
    private val selectedDrawableResId: Int
    private val selectedDrawable: Drawable
    private val normalColor: Int
    private var selectedColor: Int
    private val normalTextSize: Float
    private val selectedTextSize: Float
    private val tabText: String

    init {
        val contentView = LayoutInflater.from(context).inflate(R.layout.selectable_tab_view, this)
        tv_tab_text = contentView.find(R.id.tv_tab_text)
        iv_tab_icon = contentView.find(R.id.iv_tab_icon)
        tv_unread = contentView.find(R.id.tv_unread)
        iv_notice = contentView.find(R.id.iv_notice)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SelectableTabView)
        //可能不仅是区分颜色，所以区分两种drawable
        normalDrawableResId = typedArray.getResourceId(R.styleable.SelectableTabView_drawableNormal, R.drawable.ic_looks_one_black_24dp)
        selectedDrawableResId = typedArray.getResourceId(R.styleable.SelectableTabView_drawableSelected, R.drawable.ic_looks_one_blue_24dp)
        selectedDrawable = context.resources.getDrawable(selectedDrawableResId)
        normalColor = typedArray.getColor(R.styleable.SelectableTabView_colorNormal, Color.GRAY)
        selectedColor = typedArray.getColor(R.styleable.SelectableTabView_colorSelected, Color.BLACK)
        DrawableCompat.setTint(selectedDrawable, selectedColor)
        normalTextSize = typedArray.getDimension(R.styleable.SelectableTabView_textSizeNormal, 12f)
        selectedTextSize = typedArray.getDimension(R.styleable.SelectableTabView_textSizeSelected, 14f)
        tabText = typedArray.getString(R.styleable.SelectableTabView_tabText)
        tabSelected = typedArray.getBoolean(R.styleable.SelectableTabView_initialSelected, false)
        typedArray.recycle()

        iv_tab_icon.setImageResource(if (tabSelected) selectedDrawableResId else normalDrawableResId)
        tv_tab_text.setTextColor(if (tabSelected) selectedColor else normalColor)
        tv_tab_text.textSize = if (tabSelected) selectedTextSize else normalTextSize
        tv_tab_text.text = tabText
    }

    private fun handleTabSelectChanged() {
        val fromTextSize = if (tabSelected) normalTextSize else selectedTextSize
        val endTextSize = if (tabSelected) selectedTextSize else normalTextSize
        val fromColor = if (tabSelected) normalColor else selectedColor
        val endColor = if (tabSelected) selectedColor else normalColor
        val drawable = if (tabSelected) selectedDrawable else context.resources.getDrawable(normalDrawableResId)

        iv_tab_icon.setImageDrawable(drawable)
        val sizeAnimator = ObjectAnimator.ofFloat(tv_tab_text, "textSize", fromTextSize, endTextSize)
        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), fromColor, endColor)
        colorAnimator.addUpdateListener { animation -> tv_tab_text.textColor = animation.animatedValue as Int }

        val set = AnimatorSet()
        set.play(sizeAnimator).with(colorAnimator)
        set.duration = ANIMATION_DURATION
        set.start()
    }

    private fun showNotice() {
        tv_unread.visibility = View.GONE
        iv_notice.visibility = if (hasNotice) View.VISIBLE else View.GONE
    }

    private fun showUnread() {
        iv_notice.visibility = View.GONE
        tv_unread.text = if (unreadCount >= 100) UNREAD_MAX_TEXT else unreadCount.toString()
        tv_unread.visibility = if (unreadCount <= 0) View.GONE else View.VISIBLE
    }
}
