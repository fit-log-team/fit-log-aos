package com.example.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.ui.map.MapScreen
import com.example.presentation.ui.theme.FitLogTheme
import com.skt.tmap.TMapView

@Composable
fun MainNavHost(tMapView: TMapView) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route?.let { currentRoute ->
        MainRoute.values().find { route -> route.route == currentRoute }
    } ?: MainRoute.HOME

    Surface {
        Scaffold(

            content = { padding ->
                NavHost(
                    modifier = Modifier.padding(padding),
                    navController = navController,
                    startDestination = MainRoute.HOME.route,
                ) {
                    composable(MainRoute.HOME.route) {
                        MapScreen(tMapView)
                    }
                    composable(MainRoute.MENU_1.route) {
                        Menu1Screen()
                    }
                    composable(MainRoute.MENU_2.route) {
                        Menu2Screen()
                    }
                }
            },
            bottomBar = {
                MainBottomBar(
                    currentRoute = currentRoute,
                    navController = navController
                )
            }
        )
    }
}

@Preview
@Composable
fun MainNavHostPreview() {
    FitLogTheme {
//        MainNavHost(null)
    }
}