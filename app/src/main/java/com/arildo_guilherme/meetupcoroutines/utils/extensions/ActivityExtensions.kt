package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.transition.TransitionManager
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import com.arildo_guilherme.meetupcoroutines.utils.bindingproperties.ActivityBindingProperty

fun Context.isConnected(): Boolean {
    val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
    return networkInfo?.isConnected == true
}

fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)

inline fun <reified T : ViewDataBinding> T.animateTransitionOnRebind() {
    addOnRebindCallback(object : OnRebindCallback<T>() {
        override fun onPreBind(binding: T?): Boolean {
            TransitionManager.beginDelayedTransition(binding?.root as ViewGroup)
            return super.onPreBind(binding)
        }
    })
}