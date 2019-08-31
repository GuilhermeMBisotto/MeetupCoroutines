package com.arildo_guilherme.meetupcoroutines.utils.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.arildo_guilherme.meetupcoroutines.utils.bindingadapters.helpers.loadAppCompatImageView

@BindingAdapter(
    "bind:imageSet"
)
fun AppCompatImageView.imageFromUrl(imageUrl: String?) {
    loadAppCompatImageView(this, imageUrl)
}