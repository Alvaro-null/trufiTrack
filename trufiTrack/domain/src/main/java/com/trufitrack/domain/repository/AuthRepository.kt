package com.trufitrack.domain.repository

import com.trufitrack.domain.model.Conductor

interface AuthRepository {
    suspend fun login(correo: String, contrasena: String): Result<Conductor?>
    suspend fun register(conductor: Conductor, contrasena: String): Result<Unit>
    suspend fun logout()
    fun isUserLoggedIn(): Boolean
}
