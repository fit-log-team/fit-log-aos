package com.example.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.presentation.R
import com.example.presentation.ui.theme.FitLogTheme
import com.example.presentation.util.UiController
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //_ TODO 스플래시 정책이 아직 없어서 임시 스플래시 로딩 2초
        setContent {
            FitLogTheme {
                var loadingTime by remember { mutableStateOf(0) }
                LaunchedEffect(Unit) {
                    delay(2000)
                    loadingTime++
                }

                LaunchedEffect(loadingTime) {
                    if (loadingTime == 1) {
                        UiController.addActivity(this@SplashActivity, LoginActivity::class)
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.logo_name),
                            contentDescription = "앱 로고",
                            modifier = Modifier.fillMaxWidth()
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logo_2),
                            contentDescription = "앱 로고",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}