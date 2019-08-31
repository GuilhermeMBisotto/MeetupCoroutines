package com.arildo_guilherme.meetupcoroutines

import android.app.Application
import com.arildo_guilherme.data.di.getDataModules
import com.arildo_guilherme.meetupcoroutines.di.getAppModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            printLogger()
            androidContext(this@AppApplication)
            modules(getModules())
        }
    }

    private fun getModules(): MutableList<Module> {
        val modules: MutableList<Module> = arrayListOf()
        modules.addAll(getDataModules())
        modules.addAll(getAppModules())
        return modules
    }
}