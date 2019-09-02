package com.arildo_guilherme.meetupcoroutines.ui

import androidx.core.util.Pair
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityMainBinding
import com.arildo_guilherme.meetupcoroutines.ui.animations.AnimationsActivity
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersActivity
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersMultiCoroutinesActivity
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersSingleCoroutinesActivity
import com.arildo_guilherme.meetupcoroutines.ui.coffee.CoffeeActivity
import com.arildo_guilherme.meetupcoroutines.utils.extensions.launchActivity
import com.arildo_guilherme.meetupcoroutines.utils.extensions.launchActivityForSharedElements
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @ObsoleteCoroutinesApi
    override fun subscribeUi() {
        super.subscribeUi()

        binding.btnAnimations.setOnClickListener {
            launchActivityForSharedElements<AnimationsActivity>(
                null,
                Pair(btnAnimations, getString(R.string.containerAnimation))
            )
        }

        binding.btnCoffee.setOnClickListener {
            launchActivity<CoffeeActivity> { }
        }

        binding.btnSingleRequests.setOnClickListener {
            launchActivity<CharactersSingleCoroutinesActivity> { }
        }

        binding.btnMultipleRequests.setOnClickListener {
            launchActivity<CharactersMultiCoroutinesActivity> { }
        }

        binding.btnCharacters.setOnClickListener {
            launchActivity<CharactersActivity> { }
        }
    }
}
