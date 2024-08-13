package com.example.presentation.map.map

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.map.MapViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.PolylineOverlay
import com.naver.maps.map.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(viewModel: MapViewModel = hiltViewModel()) {
    Log.i("MapScreen","TEST_LOG1")

    var paths by remember {
        mutableStateOf<List<LatLng>>(emptyList())
    }
    LaunchedEffect(Unit) {
        viewModel.requestDirection()

        launch {
            viewModel.directionFlow.collect {
                val list = mutableListOf<LatLng>()
                it.route.traoptimal[0].path.forEach { location ->
                    list.add(LatLng(location[1], location[0]))
                }

                paths = list

            }
        }
    }

    if (paths.size > 0) {
        val seoul = paths[0]
        val cameraPositionState = rememberCameraPositionState {
            // 카메라 초기 위치를 설정합니다.
            position = CameraPosition(seoul, 13.0)
        }

        Box(Modifier.fillMaxSize()) {
            val width = 3.dp
            val patternInterval = 3.dp
            NaverMap(cameraPositionState = cameraPositionState) {
                PolylineOverlay(
                    width = width,
                    coords = paths,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}