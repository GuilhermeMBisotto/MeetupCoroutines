package com.arildo_guilherme.meetupcoroutines.di

import com.arildo_guilherme.data.characters.CharactersRepositoryImpl
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersMultiCoroutinesViewModel
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersSingleCoroutinesViewModel
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersViewModel
import com.arildo_guilherme.meetupcoroutines.ui.coffee.CoffeeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
private val appViewModelModule = module {
    viewModel { CharactersMultiCoroutinesViewModel(get() as CharactersRepositoryImpl) }
    viewModel { CharactersSingleCoroutinesViewModel(get() as CharactersRepositoryImpl) }
    viewModel { CharactersViewModel(get() as CharactersRepositoryImpl) }
    viewModel { CoffeeViewModel() }
}

@ObsoleteCoroutinesApi
fun getAppModules(): List<Module> {
    return listOf(appViewModelModule)
}