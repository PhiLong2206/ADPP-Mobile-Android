package com.adpp.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adpp.mobile.core.designsystem.theme.ADPPTheme
import com.adpp.mobile.core.navigation.ADPPNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ADPPTheme {
                ADPPNavGraph()
            }
        }
    }
}

