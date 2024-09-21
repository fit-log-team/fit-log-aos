package com.example.presentation.ui.component.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.ui.theme.color_625b71
import com.example.presentation.ui.theme.color_white

@Composable
fun StepProcessUI() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(color_625b71)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_main_stars),
            contentDescription = "stars",
            tint = color_white
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = "개인 50,000걸음 걷기",
            style = TextStyle(
                color = color_white
            )
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "right",
            tint = color_white
        )

    }
}