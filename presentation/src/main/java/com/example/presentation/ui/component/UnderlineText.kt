package com.example.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

/**
 * 밑줄 친 텍스트 버튼
 *
 * @param title     밑줄 친 버튼 제목
 * @param onClick   버튼 이벤트 처리
 */
@Composable
fun UnderlineText(title: String, onClick: () -> Unit) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                append(title)
            }
        },
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        modifier = Modifier.clickable { onClick() }
    )
}