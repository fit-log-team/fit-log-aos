package com.example.presentation.ui.map

import android.view.View
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.presentation.ui.TMapFragment
import com.skt.tmap.TMapView
import timber.log.Timber

@Composable
fun MapScreen(map: TMapView) {
    val context = LocalContext.current
    val fragmentManager = (context as AppCompatActivity).supportFragmentManager

    val containerId = remember { View.generateViewId() }

    AndroidView(
        factory = { context ->
            FragmentContainerView(context).apply {
                id = containerId
            }
        }
    )

    LaunchedEffect(containerId) {
        val existingFragment = fragmentManager.findFragmentById(containerId)
        if (existingFragment == null) {
            val fragment = TMapFragment(map)
            fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commit() // Ensure immediate execution
        }
    }
}