package com.ladddd.baselib.componet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxAppCompatDialogFragment

/**
 * Created by 陈伟达 on 2017/12/20.
 *
 * 懒加载
 */
abstract class BaseFragment : RxAppCompatDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    abstract fun getLayoutResId() : Int

    abstract fun initViews()
}