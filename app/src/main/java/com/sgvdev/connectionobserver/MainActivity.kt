package com.sgvdev.connectionobserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.sgvdev.connectionobserver.ui.theme.ConnectionObserverTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private lateinit var connectionObserver: ConnectionObserver
    private var status = ConnectionObserver.Status.UNAVAILABLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeConnection()
        setContent {
            ConnectionObserverTheme {
                NetworkStatusText(
                    connectionObserver.observe()
                        .collectAsState(initial = ConnectionObserver.Status.UNAVAILABLE).value
                )
            }
        }
    }

    private fun observeConnection() {
        connectionObserver = NetworkConnectionObserver(applicationContext)
//        connectionObserver.observe().onEach {
//            status = it
//        }.launchIn(lifecycleScope)
    }

    @Composable
    fun NetworkStatusText(
        status: ConnectionObserver.Status
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Network status: $status"
            )
        }
    }
}