package com.example.domain.model.workdirection.response

import kotlinx.serialization.Serializable

data class Features(
    val type: String,
    val geometry: Geometry,
    val properties: Properties
)
