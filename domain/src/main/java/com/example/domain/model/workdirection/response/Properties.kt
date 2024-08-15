package com.example.domain.model.workdirection.response

data class Properties(
    val totalDistance: Int,
    val totalTime: Int,
    val index: Int,
    val pointIndex: Int,
    val name: String,
    val description: String,
    val direction: String,
    val nearPoiName: String,
    val nearPoiX: String,
    val nearPoiY: String,
    val intersectionName: String,
    val facilityType: String,
    val facilityName: String,
    val turnType: Int,
    val pointType: String
)
