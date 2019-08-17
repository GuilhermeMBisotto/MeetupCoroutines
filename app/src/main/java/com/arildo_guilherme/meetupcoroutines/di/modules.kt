package com.arildo_guilherme.meetupcoroutines.di

import com.arildo_guilherme.data.characters.CharactersRepositoryImpl
import com.arildo_guilherme.meetupcoroutines.ui.characters.CharactersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val appViewModelModule = module {
    viewModel { CharactersViewModel(get() as CharactersRepositoryImpl) }
}

fun getAppModules(): List<Module> {
    return listOf(appViewModelModule)
}