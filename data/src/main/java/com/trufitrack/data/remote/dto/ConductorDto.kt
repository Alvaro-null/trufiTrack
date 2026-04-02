package com.trufitrack.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConductorDto(
    @SerialName("id_conductor")    val idConductor: Int = 0,
    @SerialName("nombre_completo") val nombreCompleto: String,
    @SerialName("celular")         val celular: String,
    @SerialName("licencia")        val licencia: String,
    @SerialName("correo")          val correo: String,
    @SerialName("contrasena")      val contrasena: String,
    @SerialName("estado")          val estado: String = "activo"
)
