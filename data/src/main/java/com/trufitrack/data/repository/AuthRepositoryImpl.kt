package com.trufitrack.data.repository

import com.trufitrack.core.supabaseClient
import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.repository.AuthRepository
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override suspend fun login(correo: String, contrasena: String): Result<ConductorDto?> {
        return try {
            val conductor = supabaseClient.postgrest["conductor"]
                .select {
                    eq("correo", correo)
                    eq("contrasena", contrasena)
                }
                .decodeSingleOrNull<ConductorDto>()
            Result.success(conductor)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(conductor: ConductorDto): Result<Unit> {
        return try {
            supabaseClient.postgrest["conductor"].insert(conductor)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {
        // No session to clear, just local
    }

    override fun isUserLoggedIn(): Boolean {
        // For now, assume not, or use shared prefs
        return false
    }
}