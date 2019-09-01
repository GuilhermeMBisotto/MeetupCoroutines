package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.animation.ValueAnimator
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.arildo_guilherme.meetupcoroutines.R

fun ImageView.fadeColor(color: Int) {
    val from = ContextCompat.getColor(this.context, R.color.almost_black)
    val to = ContextCompat.getColor(this.context, color)

    val anim = ValueAnimator()
    anim.setIntValues(from, to)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener { valueAnimator -> this.setColorFilter(valueAnimator.animatedValue as Int) }

    anim.duration = 1200
    anim.start()
}