package com.example.presentation.ui

import android.graphics.PointF
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentation.config.TMapConfig
import com.example.presentation.databinding.ActivityTmapBinding
import com.example.presentation.model.LocationItem
import com.example.presentation.util.MemoryCacheUtil
import com.example.presentation.util.PermissionUtils
import com.example.presentation.util.intentSerializable
import com.example.presentation.viewmodel.TMapViewModel
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import com.skt.Tmap.poi_item.TMapPOIItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList

@AndroidEntryPoint
class TMapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTmapBinding
    private val viewModel by viewModels<TMapViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTmapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        addTMap()
    }

    private fun addTMap() {
        val currentLocation = MemoryCacheUtil.currentLocation
        binding.clMap.addView(getMapLayout(currentLocation!!.latitude, currentLocation.longitude))
    }

    private fun getMapLayout(lat: Double, long: Double) = TMapView(this).apply {
        setSKTMapApiKey(TMapConfig.API_KEY)
        setLanguage(TMapView.LANGUAGE_KOREAN)
        setIconVisibility(true)
        zoomLevel = TMapConfig.ZOOM_LEVEL
        mapType = TMapView.MAPTYPE_STANDARD
        setCenterPoint(long, lat)
        setOnClickListenerCallBack(object: TMapView.OnClickListenerCallback {
            override fun onPressEvent(
                markerItem: ArrayList<TMapMarkerItem>?,
                poiItemList: ArrayList<TMapPOIItem>?,
                tMapPoint: TMapPoint?,
                pointF: PointF?
            ): Boolean {
                tMapPoint?.let { point ->
                    lifecycleScope.launch {
                        viewModel.requestWorkDirection(point)
                    }
                }
                return false
            }

            override fun onPressUpEvent(
                p0: ArrayList<TMapMarkerItem>?,
                p1: ArrayList<TMapPOIItem>?,
                p2: TMapPoint?,
                p3: PointF?
            ): Boolean {
                return false
            }
        })
    }
}