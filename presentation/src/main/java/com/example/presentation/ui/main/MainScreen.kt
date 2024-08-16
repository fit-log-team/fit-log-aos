package com.example.presentation.ui.main

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.presentation.ui.TMapActivity
import com.example.presentation.util.PermissionUtils
import com.example.presentation.util.UiController
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var isLocationInit by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
    ) {
        scope.launch {
            PermissionUtils.getLocation(context).collect {
                Timber.i("loc: $it")
                isLocationInit = true
            }
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
    }

    if (isLocationInit) {
        UiController.addActivity(
            context = context,
            targetActivity = TMapActivity::class,
        )
    }

}