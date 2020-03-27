package msousa.dev.tokenlab_challenge.domain

import android.content.Context
import android.net.ConnectivityManager

object Utils {
    fun isOffline(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo == null || !networkInfo.isConnected
    }
}