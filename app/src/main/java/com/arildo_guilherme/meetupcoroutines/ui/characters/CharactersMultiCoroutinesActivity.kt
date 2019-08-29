package com.arildo_guilherme.meetupcoroutines.ui.characters

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.arildo_guilherme.meetupcoroutines.R
import com.arildo_guilherme.meetupcoroutines.base.BaseActivity
import com.arildo_guilherme.meetupcoroutines.databinding.ActivityCharactersMultiCoroutinesBinding
import com.arildo_guilherme.meetupcoroutines.ui.characters.adapters.CharactersAdapter
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

/* annotation class ExperimentalCoroutinesApi (source)
 * Marks declarations that are still experimental in coroutines API,
 * which means that the design of the corresponding declarations has open
 * issues which may (or may not) lead to their changes in the future.
 * Roughly speaking, there is a chance that those declarations will
 * be deprecated in the near future or the semantics of their behavior may
 * change in some way that may break some code.
 */
@ExperimentalCoroutinesApi

/* annotation class ObsoleteCoroutinesApi (source)
 * Marks declarations that are obsolete in coroutines API,
 * which means that the design of the corresponding declarations has
 * serious known flaws and they will be redesigned in the future.
 * Roughly speaking, these declarations will be deprecated in the
 * future but there is no replacement for them yet, so they cannot be deprecated right away.
 */
@ObsoleteCoroutinesApi
class CharactersMultiCoroutinesActivity :
    BaseActivity<ActivityCharactersMultiCoroutinesBinding>(R.layout.activity_characters_multi_coroutines) {

    private val multiCoroutinesViewModel: CharactersMultiCoroutinesViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = multiCoroutinesViewModel
        binding.recyclerViewCharacters.adapter = adapter

        launch {
            withContext(Dispatchers.IO) {
                multiCoroutinesViewModel.getCharacters()
            }
        }

        multiCoroutinesViewModel.time.observe(this, Observer {
            Toast.makeText(this, "Request time: $it", Toast.LENGTH_LONG).show()
        })
    }
}