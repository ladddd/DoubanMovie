package com.ladddd.douban.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ladddd.baselib.extension.loadUrl
import com.ladddd.douban.R
import com.ladddd.douban.bean.MovieSubject

/**
 * Created by 陈伟达 on 2017/12/8.
 */
class MovieAdapter : BaseQuickAdapter<MovieSubject, BaseViewHolder>(R.layout.item_movie) {

    override fun convert(helper: BaseViewHolder?, item: MovieSubject?) {
        helper?.setText(R.id.tv_movie_name, item?.title)

        helper?.getView<ImageView>(R.id.iv_movie_image)?.loadUrl(item?.images?.small)
    }
}