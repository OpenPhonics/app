package com.openphonics.android.backend.connectivity

import android.net.ConnectivityManager
import com.openphonics.android.core.connectivity.ConnectionState
import com.openphonics.android.core.connectivity.ConnectivityObserver
import com.openphonics.android.backend.utils.currentConnectivityState
import com.openphonics.android.backend.utils.observeConnectivityAsFlow
import kotlinx.coroutines.flow.Flow

class ConnectivityObserverImpl(
    private val connectivityManager: ConnectivityManager
) : ConnectivityObserver {

    override val connectionState: Flow<ConnectionState>
        get() = connectivityManager.observeConnectivityAsFlow()

    override val currentConnectionState: ConnectionState
        get() = connectivityManager.currentConnectivityState
}
