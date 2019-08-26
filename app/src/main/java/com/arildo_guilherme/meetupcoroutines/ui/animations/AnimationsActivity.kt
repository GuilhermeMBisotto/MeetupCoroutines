package com.arildo_guilherme.meetupcoroutines.ui.animations

import android.os.Bundle
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityAnimationsBinding
import com.arildo_guilherme.meetupcoroutines.utils.extensions.slideDown
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AnimationsActivity : BaseActivity<ActivityAnimationsBinding>(R.layout.activity_animations) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch {
            delay(1_000)
            binding.tvTitle.slideDown(500)
        }
    }
}
