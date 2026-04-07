package com.trufitrack.domain.model

data class Conductor(
    val idConductor: Int = 0,
    val nombreCompleto: String,
    val celular: String? = null,
    val licencia: String,
    val correo: String,
    val estado: String = "activo"
)
