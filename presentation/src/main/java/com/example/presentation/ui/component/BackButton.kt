package com.example.presentation.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 뒤로가기 버튼
 *
 * @param modifier
 */
@Composable
fun BackButton(modifier: Modifier) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = "뒤로 가기"
    )
}