package com.arildo_guilherme.meetupcoroutines.di

import com.arildo_guilherme.data.characters.CharactersRepositoryImpl
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersMultiRequestViewModel
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersSingleRequestViewModel
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@ObsoleteCoroutinesApi
private val appViewModelModule = module {
    viewModel { CharactersMultiRequestViewModel(get() as CharactersRepositoryImpl) }
    viewModel { CharactersSingleRequestViewModel(get() as CharactersRepositoryImpl) }
}

@ObsoleteCoroutinesApi
fun getAppModules(): List<Module> {
    return listOf(appViewModelModule)
}