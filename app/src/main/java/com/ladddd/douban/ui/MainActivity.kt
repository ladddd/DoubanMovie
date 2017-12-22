package com.ladddd.douban.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.ladddd.baselib.componet.BaseActivity
import com.ladddd.douban.R
import com.ladddd.douban.widget.HomeBottomSheet
import org.jetbrains.anko.find

/**
 *  主页面
 */
class MainActivity : BaseActivity() {

    private val fragments : Array<Fragment> = Array(3, {
        i -> when(i) {
            0 -> CinemaFragment()
            1 -> DiscoverFragment()
            else -> UserFragment()
        }
    })

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        val vp_home = find<ViewPager>(R.id.vp_home)
        vp_home.offscreenPageLimit = 3
        vp_home.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return fragments[position]
            }

            override fun getCount(): Int {
                return fragments.size
            }
        }

        val bottom_sheet = find<HomeBottomSheet>(R.id.bottom_sheet)
        bottom_sheet.tabSelectedListener = object : HomeBottomSheet.OnTabSelectedListener {
            override fun onTabSelected(index: Int) {
                vp_home.currentItem = index
            }
        }
    }
}
