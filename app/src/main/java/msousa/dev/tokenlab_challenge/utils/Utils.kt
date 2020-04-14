package msousa.dev.tokenlab_challenge.utils

import android.content.Context
import msousa.dev.tokenlab_challenge.presentation.extensions.getConnectivityManager

object Utils {
    fun isOffline(context: Context) : Boolean {
        val connectivityManager = context.getConnectivityManager()
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo == null || !networkInfo.isConnected
    }
}