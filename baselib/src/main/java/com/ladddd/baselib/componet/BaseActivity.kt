package com.ladddd.baselib.componet

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by 陈伟达 on 2017/12/20.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResId())

        initViews()
    }

    abstract fun getLayoutResId() : Int

    abstract fun initViews()
}