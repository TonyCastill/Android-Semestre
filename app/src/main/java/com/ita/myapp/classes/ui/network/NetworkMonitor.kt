package com.ita.myapp.classes.ui.network

import android.Manifest
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ita.myapp.classes.MainActivity

class NetworkMonitor(
    private val wifiManager: WifiManager,
    private val connectivityManager: ConnectivityManager,
    private val activity: AppCompatActivity
    //MainActivity
) {
    private fun getConnectionStatus(): String {
        val networkCapabilities = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )
        val isWifiConnected = wifiManager.isWifiEnabled && networkCapabilities?.hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) == true
        val isMobileConnected = networkCapabilities?.hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) == true

        return when {
            isWifiConnected -> {
                if (
                    ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val wifiInfo: WifiInfo? = wifiManager.connectionInfo
                    val ssid = wifiInfo?.ssid?.replace("\"", "") ?: "Desconocido"
                    "Conectado a wifi: $ssid"
                } else {
                    "Conectadp a WIFI (NOMBRE NO DISPONIBLE)"
                }
            }

            isMobileConnected -> "Conectado a datos moviles"

            else -> "Sin conexion a internet"

        }
    }
}

