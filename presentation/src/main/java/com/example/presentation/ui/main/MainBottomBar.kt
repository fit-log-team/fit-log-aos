package com.example.presentation.ui.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.presentation.ui.theme.FitLogTheme

@Composable
fun MainBottomBar(
    navController: NavController,
    currentRoute: MainRoute,
) {
    MainBottomBar(
        currentRoute = currentRoute,
        onItemClick = { route ->
            if(route != currentRoute) {
                navController.navigate(route = route.route) {
                    navController.graph.startDestinationRoute?.let {
                        popUpTo(it) {
                            saveState = true
                        }
                    }
                    this.launchSingleTop = true
                    this.restoreState = true
                }
            }
        }
    )
}

@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit
) {


    Column {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth()
                .padding(bottom = 2.dp)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MainRoute.values().forEach { route ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { onItemClick(route) }) {
                        Icon(
                            imageVector = route.icon,
                            contentDescription = route.contentDescription,
                            tint = if (currentRoute == route) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.White
                            }
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun MainBottomBarPreview() {
    FitLogTheme {
        MainBottomBar(
            currentRoute = MainRoute.HOME,
            onItemClick = {}
        )
    }
}