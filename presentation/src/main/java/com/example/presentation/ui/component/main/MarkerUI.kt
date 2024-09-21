package com.example.presentation.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.theme.color_ffd8e4

@Composable
fun MarkerUI() {
    Box(modifier = Modifier
        .background(color = color_ffd8e4, shape = RoundedCornerShape(8.dp))
        .padding(4.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.ic_main_stars),
            contentDescription = "marker"
        )
    }

}