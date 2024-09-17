package com.example.presentation.ui.component

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation

/**
 * 비밀번호 텍스트 필드
 *
 * @param password 입력된 비밀번호
 * @param onValueChange 변경된 비밀번호 값 처리
 * @param modifier
 */
@Composable
fun PasswordField(
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier
) {
    // 비밀번호 입력 필드
    OutlinedTextField(
        modifier = modifier,
        value = password,
        onValueChange = onValueChange,
        label = { Text("비밀번호") },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        trailingIcon = {}
    )
}