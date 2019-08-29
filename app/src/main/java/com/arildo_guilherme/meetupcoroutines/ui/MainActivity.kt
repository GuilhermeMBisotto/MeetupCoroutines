package com.arildo_guilherme.meetupcoroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.ui.animations.AnimationsActivity
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersMultiRequestActivity
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersSingleRequestActivity
import com.arildo_guilherme.meetupcoroutines.utils.extensions.launchActivity
import com.arildo_guilherme.meetupcoroutines.utils.extensions.launchActivityForSharedElements
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAnimations.setOnClickListener {
            launchActivityForSharedElements<AnimationsActivity>(
                null,
                Pair(btnAnimations, "containerAnimation"),
                Pair(btnAnimations, "textAnimation")
            )
        }

        btnCafe.setOnClickListener {

        }

        btnSingleRequests.setOnClickListener {
            launchActivity<CharactersSingleRequestActivity> { }
        }

        btnMultipleRequests.setOnClickListener {
            launchActivity<CharactersMultiRequestActivity> { }
        }

    }
}
