package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.view.View
import android.view.ViewGroup
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager

fun View.slideDown(duration: Int = 500) {
    val originalY = this.y
    visibility = View.VISIBLE
    this.y = 0 - this.height.toFloat()
    this.animate().y(originalY).setDuration(duration.toLong()).start()
}
inline fun <T : View> T.runTransition(block: T.() -> Unit): T {
    TransitionManager.beginDelayedTransition(this.parent as ViewGroup)
    block()
    return this
}

inline fun <T : View> T.runTransition(duration: Long, block: T.() -> Unit): T {
    val transition = AutoTransition()
    transition.duration = duration
    TransitionManager.go(Scene(this.parent as ViewGroup), transition)
    block()
    return this
}