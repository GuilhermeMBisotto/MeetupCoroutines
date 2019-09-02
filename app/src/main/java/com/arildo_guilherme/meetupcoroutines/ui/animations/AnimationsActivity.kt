package com.arildo_guilherme.meetupcoroutines.ui.animations

import android.os.Bundle
import android.view.View
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityAnimationsBinding
import com.arildo_guilherme.meetupcoroutines.utils.extensions.fadeColor
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
}
