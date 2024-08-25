package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetWorkDirectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {
    private val _isMapReady = MutableLiveData(false)
    val isMapReady: LiveData<Boolean> = _isMapReady

    fun setMapReady(ready: Boolean) {
        _isMapReady.value = ready
    }

}