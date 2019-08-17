package com.arildo_guilherme.meetupcoroutines.utils.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arildo_guilherme.meetupcoroutines.utils.bindingadapters.helpers.BindableAdapter

@BindingAdapter("data")
fun <T> RecyclerView.setRecyclerViewProperties(data: T?) {
    if (this.adapter is BindableAdapter<*>) {
        (this.adapter as BindableAdapter<T>).setData(data)
    }
}