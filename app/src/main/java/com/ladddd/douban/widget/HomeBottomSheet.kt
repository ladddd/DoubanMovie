package com.ladddd.douban.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.ladddd.douban.R
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by 陈伟达 on 2017/6/23.
 *
 */

class HomeBottomSheet(context: Context, attributeSet: AttributeSet? = null) : LinearLayout(context, attributeSet) {

    private val tabArray = ArrayList<SelectableTabView>()

    var tabSelectedListener : OnTabSelectedListener? = null

    init {
        val contentView = LayoutInflater.from(context).inflate(R.layout.home_bottom_sheet, this) as View
        val tab1 = contentView.find<SelectableTabView>(R.id.tab1)
        val tab2 = contentView.find<SelectableTabView>(R.id.tab2)
        val tab3 = contentView.find<SelectableTabView>(R.id.tab3)

        tab1.tabSelected = true
        tabArray.add(tab1)
        tabArray.add(tab2)
        tabArray.add(tab3)

        for (i in tabArray.indices) {
            tabArray[i].setOnClickListener({ setTabSelected(i) })
        }
    }

    private fun setTabSelected(index: Int) {
        for (i in tabArray.indices) {
            tabArray[i].tabSelected = i == index
        }
        tabSelectedListener?.onTabSelected(index)
    }

    interface OnTabSelectedListener {
        fun onTabSelected(index: Int)
    }
}
