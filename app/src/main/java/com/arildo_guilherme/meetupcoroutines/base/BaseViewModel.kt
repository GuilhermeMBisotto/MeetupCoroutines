package com.arildo_guilherme.meetupcoroutines.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val viewModelExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + viewModelExceptionHandler

    private val _internetHandlerLiveData = MutableLiveData<Boolean>()
    val internetHandlerLiveData: LiveData<Boolean> =
        Transformations.map(_internetHandlerLiveData) { it }

    fun setInternetConnectionStatus(isConnected: Boolean) =
        _internetHandlerLiveData.postValue(isConnected)

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}