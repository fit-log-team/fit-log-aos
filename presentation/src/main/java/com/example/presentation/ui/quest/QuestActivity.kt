package com.example.presentation.ui.quest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.presentation.ui.theme.FitLogTheme
import com.example.presentation.viewmodel.QuestViewModel

class QuestActivity : ComponentActivity() {
    private val viewModel by viewModels<QuestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    QuestScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}