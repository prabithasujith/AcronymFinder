package com.prabitha.acronymfinder.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


fun isNetworkConnectionAvailable(context: Context): Boolean {
    val connectivityManager = getSystemService(context, ConnectivityManager::class.java)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities =
            connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
        networkCapabilities != null
    } else {
        // below Marshmallow
        val activeNetwork = connectivityManager?.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting == true && activeNetwork.isAvailable
    }


}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
