package com.arildo_guilherme.meetupcoroutines.utils.extensions

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.core.util.Pair
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

inline fun <reified T : Any> newIntent(
    context: Context,
    noinline init: Intent.() -> Unit = {}
): Intent {
    val intent = Intent(context, T::class.java)
    intent.init()
    return intent
}

inline fun <reified T : Any> Activity.launchActivityForSharedElements(
    args: Bundle? = null,
    vararg pairs: Pair<View, String>,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this, init)
    args?.run {
        intent.putExtras(args)
    }

    val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
        this,
        *pairs
    )
    startActivity(intent, options.toBundle())
}
inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    startActivityForResult(newIntent<T>(this, init), requestCode, options)
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    startActivity(newIntent<T>(this, init), options)
}