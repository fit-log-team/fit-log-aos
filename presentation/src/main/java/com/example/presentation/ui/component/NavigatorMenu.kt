package com.example.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun NavigatorMenu(modifier: Modifier? = Modifier, title: String, imageVector: ImageVector) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector = imageVector, contentDescription = "")
        Text(text = title)

    }
}