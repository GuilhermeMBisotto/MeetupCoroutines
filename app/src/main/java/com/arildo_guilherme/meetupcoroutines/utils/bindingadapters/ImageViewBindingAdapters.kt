package com.arildo_guilherme.meetupcoroutines.utils.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(
    "bind:imageSet"
)
fun AppCompatImageView.imageFromUrl(imageUrl: String?) {
    loadAppCompatImageView(this, imageUrl)
}

fun loadAppCompatImageView(image: AppCompatImageView, imageUrl: String?) {
    Glide.with(image.context)
        .load("$imageUrl.jpg")
        .apply(
            RequestOptions()
                .dontTransform()
                .error(android.R.color.transparent)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(image)
}