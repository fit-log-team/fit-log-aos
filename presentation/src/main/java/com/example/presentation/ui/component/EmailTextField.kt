package com.example.presentation.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

/**
 * 이메일 텍스트 필드
 *
 * @param email 입력된 이메일
 * @param onValueChange 변경된 이메일 값 처리
 * @param modifier
 */
@Composable
fun EmailTextField(
    email: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = email,
        onValueChange = onValueChange,
        label = { Text("이메일") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}
