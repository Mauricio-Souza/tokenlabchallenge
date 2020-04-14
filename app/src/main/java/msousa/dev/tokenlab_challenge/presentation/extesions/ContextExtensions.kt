package msousa.dev.tokenlab_challenge.presentation.extesions

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

fun Context.getConnectivityManager() = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager