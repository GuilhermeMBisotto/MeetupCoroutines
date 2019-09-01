package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.animation.ValueAnimator
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.arildo_guilherme.meetupcoroutines.R

fun TextView.fadeColor(color: Int) {
    val from = ContextCompat.getColor(this.context, R.color.almost_black)
    val to = ContextCompat.getColor(this.context, color)

    val anim = ValueAnimator()
    anim.setIntValues(from, to)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener { valueAnimator -> this.setTextColor(valueAnimator.animatedValue as Int) }

    anim.duration = 1200
    anim.start()
}