package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.view.View

fun View.slideDown(duration: Int = 500) {
    val originalY = this.y
    visibility = View.VISIBLE
    this.y = 0 - this.height.toFloat()
    this.animate().y(originalY).setDuration(duration.toLong()).start()
}