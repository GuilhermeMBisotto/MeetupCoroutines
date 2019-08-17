package com.arildo_guilherme.meetupcoroutines.base

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.arildo_guilherme.meetupcoroutines.NetworkChangeReceiver
import com.arildo_guilherme.meetupcoroutines.utils.extensions.activityBinding
import com.arildo_guilherme.meetupcoroutines.utils.extensions.animateTransitionOnRebind
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val resId: Int
) : AppCompatActivity(), CoroutineScope, NetworkChangeReceiver.ConnectivityReceiverListener {

    private val activityExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + activityExceptionHandler

    private var networkChangeReceiver = NetworkChangeReceiver()

    val binding by activityBinding<T>(resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.animateTransitionOnRebind<ViewDataBinding>()

        subscribeUi()

        registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onResume() {
        super.onResume()
        NetworkChangeReceiver.connectivityReceiverListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        onConnectionChanged(isConnected)
    }

    /**
     * Override this method to observe livedata objects (optional)
     */
    open fun subscribeUi() {}

    /**
     * Override this method to check connectivity (optional)
     */
    open fun onConnectionChanged(isConnected: Boolean) {}
}
