package com.statemanagementexample.statemanagementexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.statemanagementexample.statemanagementexample.a.RegisterScreenARoot
import com.statemanagementexample.statemanagementexample.b.RegisterScreenBRoot
import com.statemanagementexample.statemanagementexample.c.RegisterScreenCRoot
import com.statemanagementexample.statemanagementexample.ui.theme.StateManagementExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateManagementExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
//                        RegisterScreenARoot()
//                        RegisterScreenBRoot()
                        RegisterScreenCRoot()
                    }
                }
            }
        }
    }
}

