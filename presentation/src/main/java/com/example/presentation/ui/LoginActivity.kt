package com.example.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.presentation.ui.login.LoginScreen
import com.example.presentation.ui.theme.FitLogTheme
import com.example.presentation.util.UiController
import com.example.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        clickLogin = { clickLogin() },
                        clickGoogleLogin = { clickGoogleLogin() },
                        gotoSignup = { gotoSignup() })
                }
            }
        }
    }

    /**
     * 로그인
     */
    private fun clickLogin() {
        UiController.addActivity(this@LoginActivity, MainActivity::class)
    }

    /**
     * 구글 로그인
     */
    private fun clickGoogleLogin() {

    }

    /**
     * 회원가입 창 이동
     */
    private fun gotoSignup() {
        UiController.addActivity(this@LoginActivity, SignupActivity::class)
    }
}