package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.poi.response.PoiItem
import com.example.domain.model.workdirection.response.Features
import com.example.domain.usecase.GetPoiUseCase
import com.example.domain.usecase.GetWorkDirectionUseCase
import com.skt.tmap.TMapPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPoiUseCase: GetPoiUseCase,
    private val getWorkDirectionUseCase: GetWorkDirectionUseCase
): ViewModel(), ContainerHost<MapDataState, MapSideEffect> {

    override val container: Container<MapDataState, MapSideEffect> = container(
        initialState = MapDataState(),
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                intent {
                    postSideEffect(MapSideEffect.Toast(throwable.message.orEmpty()))
                }
            }
        }
    )

    fun onSearchPoi(keyword: String) = intent {
        val data = getPoiUseCase.invoke(keyword).getOrThrow()
        Timber.i("onSearchPoi() | data: $data")
    }

    fun searchWorkDirection(start: TMapPoint, end: TMapPoint) = intent {
//        val startAddress = async(Dispatchers.IO) { TMapData().convertGpsToAddress(start.latitude, start.longitude) }.await()
//        val endAddress = async(Dispatchers.IO) { TMapData().convertGpsToAddress(end.latitude, end.longitude) }.await()
//        val data = getWorkDirectionUseCase(
//            DirectionParam(
//                startName = startAddress,
//                startX = start.longitude.toFloat(),
//                startY = start.latitude.toFloat(),
//                endX = end.longitude.toFloat(),
//                endY = end.latitude.toFloat(),
//                endName = endAddress
//            )
//        ).getOrThrow()
    }
}

data class MapDataState(
    val poiFlow: Flow<List<PoiItem>> = emptyFlow(),
    val workDirectionFlow: Flow<List<Features>> = emptyFlow()
)

sealed interface MapSideEffect {
    class Toast(val message: String): MapSideEffect
}