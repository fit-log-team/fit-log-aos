package com.example.presentation.ui

import android.content.Context
import android.graphics.Color
import android.graphics.PointF
import android.location.Location
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentation.config.TMapConfig
import com.example.presentation.databinding.ActivityTmapBinding
import com.example.presentation.util.MemoryCacheUtil
import com.example.presentation.viewmodel.TMapViewModel
import com.skt.tmap.TMapGpsManager
import com.skt.tmap.TMapPoint
import com.skt.tmap.TMapView
import com.skt.tmap.overlay.TMapMarkerItem
import com.skt.tmap.overlay.TMapPolyLine
import com.skt.tmap.poi.TMapPOIItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.ArrayList

@AndroidEntryPoint
class TMapActivity : AppCompatActivity(), TMapGpsManager.OnLocationChangedListener {
    private lateinit var binding: ActivityTmapBinding
    private val viewModel by viewModels<TMapViewModel>()
    private lateinit var tMapView: TMapView
    private lateinit var gps: TMapGpsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTmapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        collectData()
        init()
        initLayout()
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.testFlow.collect { features ->
                tMapView.zoomLevel = TMapConfig.DIRECTION_ZOOM_LEVEL
                val alTMapPoint = ArrayList<TMapPoint>()
                features.forEach {
                    if (it.geometry.type == "Point") {
                        val long = (it.geometry.coordinates as List<Double>)[0]
                        val lat = (it.geometry.coordinates as List<Double>)[1]
                        alTMapPoint.add(TMapPoint(lat, long)) // SKT타워
                    } else if (it.geometry.type == "LineString") {
                        (it.geometry.coordinates as List<List<Double>>).forEach { coord ->
                            val long = coord[0]
                            val lat = coord[1]
                            alTMapPoint.add(TMapPoint(lat, long)) // SKT타워
                        }
                    }
                }

                Timber.i("alTMapPoint: ${alTMapPoint.size}")
                val tMapPolyLine = TMapPolyLine("line1", alTMapPoint).apply {
                    lineColor = Color.BLUE
                    lineWidth = 10f
                }
                tMapView.addTMapPolyLine(tMapPolyLine)
            }
        }
    }

    private fun init() {
        gps = TMapGpsManager(this)
        gps.minTime = 1000;
        gps.minDistance = 5F;
        gps.provider = TMapGpsManager.PROVIDER_GPS
        gps.openGps()
    }

    private fun initLayout() {
        addTMap()
        binding.btSearchDirection.setOnClickListener {
            tMapView.isTrackingMode = true
            val startPoint = TMapPoint(MemoryCacheUtil.currentLocation!!.latitude, MemoryCacheUtil.currentLocation!!.longitude)
            val endPoint = TMapPoint(37.48211999999999, 126.94263)
            viewModel.requestWorkDirection(startPoint, endPoint)
        }

        binding.btMoveToCurrentLocation.setOnClickListener {
            val currentLocation = MemoryCacheUtil.currentLocation
            tMapView.setCenterPoint(currentLocation!!.latitude, currentLocation.longitude)
        }
    }

    private fun addTMap() {
        val currentLocation = MemoryCacheUtil.currentLocation
        tMapView = getMapLayout(currentLocation!!.latitude, currentLocation.longitude)
        binding.clMap.addView(tMapView)
    }

    private fun getMapLayout(lat: Double, long: Double) = TMapView(this).apply {
        setSKTMapApiKey(TMapConfig.API_KEY)
        setOnMapReadyListener {
            tMapView.apply {
                setCenterPoint(lat, long)
                addTMapMarkerItem(getCurrentMarker(lat, long))
                zoomLevel = TMapConfig.DEFAULT_ZOOM_LEVEL;
                mapType = TMapView.MapType.DEFAULT
                isCompassMode = true
                setLocationPoint(lat, long)
                setSightVisible(true)
            }
            setOnClickListenerCallback(mapClickListener)
        }
    }

    private fun getCurrentMarker(lat: Double, long: Double) = TMapMarkerItem().apply {
        id = "현위치"
        setPosition(0.5f, 1.0f)
        name = "현위치"
        tMapPoint = TMapPoint(lat, long)
    }

    private fun addMarker(point: TMapPoint) {
        tMapView.addTMapMarkerItem(TMapMarkerItem().apply {
            id = point.toString()
            setPosition(0.5f, 1.0f)
            name = point.toString()
            tMapPoint = TMapPoint(point.latitude, point.longitude)
        })
    }

    private val mapClickListener = object: TMapView.OnClickListenerCallback {
        override fun onPressDown(
            markerItem: ArrayList<TMapMarkerItem>?,
            poiItemList: ArrayList<TMapPOIItem>?,
            tMapPoint: TMapPoint?,
            pointF: PointF?
        ) {
            tMapPoint?.let { point ->
//                addMarker(point)
            }
        }

        override fun onPressUp(p0: ArrayList<TMapMarkerItem>?, p1: ArrayList<TMapPOIItem>?, p2: TMapPoint?, p3: PointF?) {}
    }

    override fun onLocationChange(location: Location?) {
        val lat = location?.latitude
        val long = location?.longitude
        tMapView.setLocationPoint(lat!!, long!!)
        tMapView.addTMapMarkerItem(getCurrentMarker(lat!!, long!!))
    }
}