package com.arildo_guilherme.meetupcoroutines.ui.animations

import android.os.Bundle
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityAnimationsBinding
import com.arildo_guilherme.meetupcoroutines.utils.extensions.slideDown
import com.google.android.material.snackbar.Snackbar
import com.arildo_guilherme.meetupcoroutines.R
import kotlinx.coroutines.*

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

            showSnackBar()
        }
    }

    private suspend fun showSnackBar() {
        delay(2000)
        Snackbar.make(binding.root, "TEST COROUTINE", Snackbar.LENGTH_LONG).show()

    }
}
