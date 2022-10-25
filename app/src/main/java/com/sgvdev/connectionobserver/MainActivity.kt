package com.sgvdev.connectionobserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sgvdev.connectionobserver.ui.theme.ConnectionObserverTheme

class MainActivity : ComponentActivity() {

    private lateinit var connectionObserver: ConnectionObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectionObserver = NetworkConnectionObserver(applicationContext)
        setContent {
            ConnectionObserverTheme {

                val status by connectionObserver.observe()
                    .collectAsState(initial = ConnectionObserver.Status.UNAVAILABLE)

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
    }
}