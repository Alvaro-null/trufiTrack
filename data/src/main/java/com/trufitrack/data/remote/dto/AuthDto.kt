package com.trufitrack.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ConductorDto(
    val id_conductor: Int? = null,
    val nombre_completo: String,
    val celular: String? = null,
    val licencia: String,
    val correo: String,
    val contrasena: String,
    val estado: String = "activo",
    val creado_en: String? = null
)

data class LoginRequest(
    val correo: String,
    val contrasena: String
)