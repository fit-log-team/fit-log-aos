package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.poi.response.PoiItem
import com.example.domain.model.workdirection.response.Features
import com.example.domain.usecase.GetPoiUseCase
import com.example.domain.usecase.GetWorkDirectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
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
}

data class MapDataState(
    val poiFlow: Flow<List<PoiItem>> = emptyFlow(),
    val workDirectionFlow: Flow<List<Features>> = emptyFlow()
)

sealed interface MapSideEffect {
    class Toast(val message: String): MapSideEffect
}