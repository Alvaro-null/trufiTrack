package com.trufitrack.data.remote.dto

import com.trufitrack.domain.model.Conductor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConductorDto(
    @SerialName("id_conductor")    val idConductor: Int?     = null,
    @SerialName("nombre_completo") val nombreCompleto: String,
    @SerialName("celular")         val celular: String?      = null,
    @SerialName("licencia")        val licencia: String,
    @SerialName("correo")          val correo: String,
    @SerialName("contrasena")      val contrasena: String,
    @SerialName("estado")          val estado: String        = "activo",
    @SerialName("creado_en")       val creadoEn: String?     = null
)

fun ConductorDto.toConductor() = Conductor(
    idConductor    = idConductor ?: 0,
    nombreCompleto = nombreCompleto,
    celular        = celular,
    licencia       = licencia,
    correo         = correo,
    estado         = estado
)
