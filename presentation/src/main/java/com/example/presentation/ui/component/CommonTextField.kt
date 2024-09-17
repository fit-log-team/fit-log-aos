package com.example.presentation.ui.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

/**
 * 공통 텍스트 필드
 *
 * @param value 입력된 값
 * @param onValueChange 변경된  값 처리
 * @param modifier
 */
@Composable
fun CommonTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
    )
}
