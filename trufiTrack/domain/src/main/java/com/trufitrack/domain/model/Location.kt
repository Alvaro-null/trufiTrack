package com.trufitrack.domain.model

data class Location(
    val idUbicacion: Long? = null,
    val idTrufi: Int,
    val latitud: Double,
    val longitud: Double,
    val velocidadKmh: Double? = null,
    val direccion: Double? = null,
    val fechaHora: String? = null
)
