package com.example.domain.model

import kotlinx.serialization.Serializable


data class DirectionResponse(
    val code: Int,
    val message: String,
    val currentDateTime: String,
    val route: Route,
)
data class Route(
    val traoptimal: List<Traoptimal>
)
data class Traoptimal(
    val summary: Summary,
    val path: List<List<Double>>,
    val section: List<Section>,
    val guide: List<Guide>
)
data class Summary(
    val start: Location,
    val goal: Goal,
    val distance: Int,
    val duration: Int,
    val etaServiceType: Int,
    val departureTime: String,
    val bbox: List<List<Double>>,
    val tollFare: Int,
    val taxiFare: Int,
    val fuelPrice: Int
)

data class Location(
    val location: List<Double>
)
data class Goal(
    val location: List<Double>,
    val dir: Int
)
data class Section(
    val pointIndex: Int,
    val pointCount: Int,
    val distance: Int,
    val name: String,
    val congestion: Int,
    val speed: Int
)
data class Guide(
    val pointIndex: Int,
    val type: Int,
    val instructions: String,
    val distance: Int,
    val duration: Int
)
