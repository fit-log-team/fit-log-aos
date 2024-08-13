package com.example.presentation.map

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.DirectionResponse
import com.example.domain.usecase.GetDirectionUseCase
import com.example.domain.usecase.GetDummyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getDirectionUseCase: GetDirectionUseCase
) : ViewModel() {

    val directionFlow = MutableSharedFlow<DirectionResponse>()

    val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i("MapViewModel","throwable: $throwable")
    }
    fun requestDirection() {
        viewModelScope.launch(ceh) {
            Log.i("MapViewModel","requestDirection()")
            val data = getDirectionUseCase().getOrThrow()
            directionFlow.emit(data)
//            bindGetDummyUseCase()
        }
    }
}