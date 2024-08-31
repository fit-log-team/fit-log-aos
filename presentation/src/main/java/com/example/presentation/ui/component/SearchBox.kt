package com.example.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox(
    modifier: Modifier,
    clickMenu: () -> Unit,
    clickSearch: () -> Unit
    ) {
    Row( modifier = modifier
        .clip(RoundedCornerShape(28.dp))
        .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            modifier = Modifier
                .padding(horizontal = 2.dp),
            onClick = clickMenu
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                tint =  Color(0xFF49454F),
                contentDescription = "Menu"
            )
        }

        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Thin,
            color = Color(0xFF49454F),
            text = "오늘의 걷기 목표는?",
        )
        
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = clickSearch
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                tint =  Color(0xFF49454F),
                contentDescription = "Search"
            )
        }
    }
}