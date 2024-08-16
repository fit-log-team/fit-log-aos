package com.example.presentation.ui

import android.graphics.PointF
import android.location.Location
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.presentation.config.TMapConfig
import com.example.presentation.databinding.ActivityTmapBinding
import com.example.presentation.util.MemoryCacheUtil
import com.example.presentation.viewmodel.TMapViewModel
import com.skt.Tmap.TMapGpsManager
import com.skt.Tmap.TMapMarkerItem
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapPolyLine
import com.skt.Tmap.TMapView
import com.skt.Tmap.poi_item.TMapPOIItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class TMapActivity : AppCompatActivity(), TMapGpsManager.onLocationChangedCallback {

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
        init()
        initLayout()
        collectData()
    }

    private fun init() {
        gps = TMapGpsManager(this)
        gps.minTime = 1000;
        gps.minDistance = 5F;
        gps.provider = TMapGpsManager.GPS_PROVIDER
        gps.OpenGps();
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.testFlow.collect { features ->
                Timber.i("DATA: $features")
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
                val tMapPolyLine = TMapPolyLine()
                tMapPolyLine.lineWidth = 2f
                for (i in alTMapPoint.indices) {
                    tMapPolyLine.addLinePoint(alTMapPoint[i])
                }
                tMapView.addTMapPolyLine("Line1", tMapPolyLine)
            }
        }
    }

    private fun initLayout() {
        addTMap()
        binding.btSearchDirection.setOnClickListener {
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
        setLanguage(TMapView.LANGUAGE_KOREAN)
        setCenterPoint(long, lat)
        addMarkerItem("1", getCurrentMarker(lat, long))
        setIconVisibility(true)
        setCompassMode(true)
        setTrackingMode(true)
        setCompassModeFix(true)
        setLocationPoint(long, lat)
        zoomLevel = TMapConfig.DEFAULT_ZOOM_LEVEL
        mapType = TMapView.MAPTYPE_STANDARD
        setOnClickListenerCallBack(mapClickListener)
    }

    private fun getCurrentMarker(lat: Double, long: Double) = TMapMarkerItem().apply {
        setPosition(0.5f, 1.0f)
        name = "현위치"
        tMapPoint = TMapPoint(lat, long)

    }

    private fun addMarker(point: TMapPoint) {
        tMapView.addMarkerItem(point.toString(), TMapMarkerItem().apply {
            setPosition(0.5f, 1.0f)
            name = point.toString()
            tMapPoint = TMapPoint(point.latitude, point.longitude)
        })
    }

    private val mapClickListener = object: TMapView.OnClickListenerCallback {
        override fun onPressEvent(
            markerItem: ArrayList<TMapMarkerItem>?,
            poiItemList: ArrayList<TMapPOIItem>?,
            tMapPoint: TMapPoint?,
            pointF: PointF?
        ): Boolean {
            tMapPoint?.let { point ->
                addMarker(point)
            }
            return false
        }

        override fun onPressUpEvent(p0: ArrayList<TMapMarkerItem>?, p1: ArrayList<TMapPOIItem>?, p2: TMapPoint?, p3: PointF?
        ): Boolean { return false }
    }

    override fun onLocationChange(location: Location?) {
        val lat = location?.latitude
        val long = location?.longitude
        val point = gps.location
        tMapView.setLocationPoint(long!!, lat!!)
        tMapView.addMarkerItem("1", getCurrentMarker(lat!!, long!!))
    }
}