package com.arildo_guilherme.meetupcoroutines.utils.bindingadapters.helpers

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun loadAppCompatImageView(image: AppCompatImageView, imageUrl: String?) {
    Glide.with(image.context)
        .load(imageUrl)
        .apply(
            RequestOptions()
                .dontTransform()
                .error(android.R.color.transparent)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(image)
}