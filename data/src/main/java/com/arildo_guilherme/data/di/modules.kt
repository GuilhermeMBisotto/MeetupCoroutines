package com.arildo_guilherme.data.di

import com.arildo_guilherme.data.RetrofitInitializer
import com.arildo_guilherme.data.characters.CharactersRepositoryImpl
import com.arildo_guilherme.data.characters.remote.datasource.CharactersRemoteDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

private val apiServiceModule = module {
    single { RetrofitInitializer().charactersApiService() }
}

private val repositoryModule = module {
    single { CharactersRepositoryImpl(get()) }
}

private val dataSourceModule = module {
    single { CharactersRemoteDataSource(get()) }
}

fun getDataModules(): List<Module> {
    return listOf(apiServiceModule, repositoryModule, dataSourceModule)
}