package com.trufitrack.domain.repository

import com.trufitrack.data.remote.dto.ConductorDto

interface AuthRepository {
    suspend fun login(correo: String, contrasena: String): Result<ConductorDto?>
    suspend fun register(conductor: ConductorDto): Result<Unit>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
}