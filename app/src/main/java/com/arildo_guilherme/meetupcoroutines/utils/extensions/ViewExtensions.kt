package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.content.ContextCompat
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.arildo_guilherme.meetupcoroutines.R

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
    transition.interpolator = AccelerateDecelerateInterpolator()
    TransitionManager.go(Scene(this.parent as ViewGroup), transition)
    block()
    return this
}

fun View.fadeColor(color: Int) {
    val from = ContextCompat.getColor(this.context, R.color.white)
    val to = ContextCompat.getColor(this.context, color)

    val anim = ValueAnimator()
    anim.setIntValues(from, to)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener { valueAnimator -> this.setBackgroundColor(valueAnimator.animatedValue as Int) }

    anim.duration = 1200
    anim.start()
}
