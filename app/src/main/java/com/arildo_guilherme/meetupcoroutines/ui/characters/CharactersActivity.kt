package com.arildo_guilherme.meetupcoroutines.ui.characters

import android.os.Bundle
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityCharactersBinding
import com.arildo_guilherme.meetupcoroutines.ui.characters.adapters.CharactersAdapter
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharactersActivity : BaseActivity<ActivityCharactersBinding>(R.layout.activity_characters) {

    private val viewModel: CharactersViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter {}
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.recyclerViewCharacters.adapter = adapter

        launch {
            withContext(Dispatchers.IO) {
                viewModel.getCharacters()
            }
        }
    }
}