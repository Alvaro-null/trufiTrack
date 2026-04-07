package com.trufitrack.domain.repository

import com.trufitrack.domain.model.Conductor

interface ConductorRepository {
    suspend fun registrarConductor(conductor: Conductor, contrasena: String): Result<Unit>
}
