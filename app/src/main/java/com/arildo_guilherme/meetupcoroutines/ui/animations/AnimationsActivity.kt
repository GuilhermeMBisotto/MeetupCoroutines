package com.arildo_guilherme.meetupcoroutines.ui.animations

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityAnimationsBinding
import com.arildo_guilherme.meetupcoroutines.utils.extensions.runTransition
import com.arildo_guilherme.meetupcoroutines.utils.extensions.slideDown
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimationsActivity : BaseActivity<ActivityAnimationsBinding>(R.layout.activity_animations) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            delay(1_000)
            binding.tvTitle.slideDown(500)

            delay(1_000)
            binding.tvWith.slideDown(500)

            delay(1_000)
            binding.tvCoroutines.slideDown(500)

            delay(1_000)
            binding.ivKotlinLogo.runTransition {
                visibility = View.VISIBLE
            }
        }

        binding.btnChange.setOnClickListener {
            binding.tvTitle.fadeColor(R.color.white)
            binding.tvWith.fadeColor(R.color.white)
            binding.tvCoroutines.fadeColor(R.color.white)
            binding.clAnimationsContainer.fadeColor(R.color.almost_black)
            binding.ivKotlinLogo.fadeColor(R.color.white)
        }
    }

    private fun TextView.fadeColor(color: Int) {
        val from = ContextCompat.getColor(this@AnimationsActivity, R.color.almost_black)
        val to = ContextCompat.getColor(this@AnimationsActivity, color)

        val anim = ValueAnimator()
        anim.setIntValues(from, to)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener { valueAnimator -> this.setTextColor(valueAnimator.animatedValue as Int) }

        anim.duration = 1200
        anim.start()
    }

    private fun View.fadeColor(color: Int) {
        val from = ContextCompat.getColor(this@AnimationsActivity, R.color.white)
        val to = ContextCompat.getColor(this@AnimationsActivity, color)

        val anim = ValueAnimator()
        anim.setIntValues(from, to)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener { valueAnimator -> this.setBackgroundColor(valueAnimator.animatedValue as Int) }

        anim.duration = 1200
        anim.start()
    }

    private fun ImageView.fadeColor(color: Int) {
        val from = ContextCompat.getColor(this@AnimationsActivity, R.color.almost_black)
        val to = ContextCompat.getColor(this@AnimationsActivity, color)

        val anim = ValueAnimator()
        anim.setIntValues(from, to)
        anim.setEvaluator(ArgbEvaluator())
        anim.addUpdateListener { valueAnimator -> this.setColorFilter(valueAnimator.animatedValue as Int) }

        anim.duration = 1200
        anim.start()
    }
}
