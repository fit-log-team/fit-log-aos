package com.example.presentation.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainRoute(
    val route: String,
    val contentDescription: String,
    val icon: ImageVector
) {
    HOME(route = "HOME", contentDescription = "HOME", Icons.Filled.Home),
    MENU_1(route = "MENU_1", contentDescription = "MENU_1", Icons.Filled.Favorite),
    MENU_2(route = "MENU_2", contentDescription = "MENU_2", Icons.Filled.LocationOn)
}