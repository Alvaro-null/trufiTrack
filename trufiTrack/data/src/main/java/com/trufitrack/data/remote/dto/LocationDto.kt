package com.trufitrack.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val id_ubicacion: Long? = null,
    val id_trufi: Int,
    val latitud: Double,
    val longitud: Double,
    val velocidad_kmh: Double? = null,
    val direccion: Double? = null,
    val fecha_hora: String? = null
)