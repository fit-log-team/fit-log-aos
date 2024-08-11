package com.example.presentation.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.map.ui.theme.FitLogTheme
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.PolygonOverlay
import com.naver.maps.map.compose.PolylineOverlay
import com.naver.maps.map.compose.rememberCameraPositionState
private val COORDS_1 = listOf(
    LatLng(37.482408, 126.941336),
    LatLng(37.484515, 126.935844),
    LatLng(37.490152, 126.929648),
    LatLng(37.492769, 126.920557),
    LatLng(37.493457, 126.924197),
)

private val COORDS_2 = listOf(
    LatLng(37.5640984, 126.9712268),
    LatLng(37.5651279, 126.9767904),
    LatLng(37.5625365, 126.9832241),
    LatLng(37.5585305, 126.9809297),
    LatLng(37.5590777, 126.974617),
)

private val HOLES = listOf(
    listOf(
        LatLng(37.5612243, 126.9768938),
        LatLng(37.5627692, 126.9795502),
        LatLng(37.5628377, 126.976066),
    ),
)
class MapActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitLogTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {


    val seoul = LatLng(37.532600, 127.024612)
    val cameraPositionState = rememberCameraPositionState {
        // 카메라 초기 위치를 설정합니다.
        position = CameraPosition(seoul, 13.0)
    }

    Box(Modifier.fillMaxSize()) {

        val width = 3.dp
        val patternInterval = 3.dp
        NaverMap {
            PolylineOverlay(
                width = width,
                coords = COORDS_1,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}