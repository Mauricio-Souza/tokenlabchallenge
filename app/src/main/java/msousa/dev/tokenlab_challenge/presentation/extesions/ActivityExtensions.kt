package msousa.dev.tokenlab_challenge.presentation.extesions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.showSnackbar(text: String) {
    Snackbar.make(findViewById<View>(android.R.id.content), text, Snackbar.LENGTH_LONG).show()
}

@SuppressLint("ObsoleteSdkInt")
inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline int: Intent.() -> Unit = {}
) {

    val intent =
        newIntent<T>(this)
    intent.int()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)