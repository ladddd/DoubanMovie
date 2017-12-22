package com.ladddd.baselib.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.ladddd.baselib.R

/**
 * Created by 陈伟达 on 2017/12/22.
 */
fun ImageView.loadUrl(url:String? = "") {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.image_default)
                    .error(R.drawable.image_default))
            .into(this)
}