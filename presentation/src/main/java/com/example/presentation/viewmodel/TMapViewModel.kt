package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Route
import com.example.domain.model.workdirection.request.DirectionParam
import com.example.domain.model.workdirection.response.Features
import com.example.domain.usecase.GetWorkDirectionUseCase
import com.example.presentation.util.PermissionUtils
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TMapViewModel @Inject constructor(
    private val getWorkDirectionUseCase: GetWorkDirectionUseCase
): ViewModel() {

    val ceh = CoroutineExceptionHandler { _, throwable ->
        Timber.i("Exception: $throwable")
    }

    fun setRoute(tMapPoint: TMapPoint) {

    }


    var testFlow = MutableSharedFlow<List<Features>>()
    fun requestWorkDirection(start: TMapPoint, end: TMapPoint) {
        viewModelScope.launch {
            val startAddress = async(Dispatchers.IO) { TMapData().convertGpsToAddress(start.latitude, start.longitude) }.await()
            val endAddress = async(Dispatchers.IO) { TMapData().convertGpsToAddress(end.latitude, end.longitude) }.await()
            val data = getWorkDirectionUseCase(DirectionParam(
                startName = startAddress,
                startX = start.longitude.toFloat(),
                startY = start.latitude.toFloat(),
                endX = end.longitude.toFloat(),
                endY = end.latitude.toFloat(),
                endName = endAddress
            )).getOrThrow()

            testFlow.emit(data)
        }

    }

    fun requestWorkDirection(tMapPoint: TMapPoint) {
        viewModelScope.launch(ceh) {
            val address = async(Dispatchers.IO) { TMapData().convertGpsToAddress(tMapPoint.latitude, tMapPoint.longitude) }.await()
            val lat = tMapPoint.latitude
            val long = tMapPoint.longitude
            val data = getWorkDirectionUseCase(DirectionParam(
                startName = "봉천역",
                startX = 126.941547.toFloat(),
                startY = 37.482099.toFloat(),
                endX = 126.924601.toFloat(),
                endY = 37.493562.toFloat(),
                endName = "보라매공원"
            )).getOrThrow()
            Timber.i("Data: $data")

//            getWorkDirectionUseCase()
        }
    }
}