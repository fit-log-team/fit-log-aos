package com.example.presentation.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * 앱 내 공통 버튼
 *
 * @param title     버튼 제목
 * @param onClick   버튼 이벤트 처리
 */
@Composable
fun CommonButton(title: String, onClick: () -> Unit, modifier: Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(title)
    }
}