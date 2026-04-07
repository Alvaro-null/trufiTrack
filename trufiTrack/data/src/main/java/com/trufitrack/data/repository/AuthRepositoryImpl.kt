package com.trufitrack.data.repository

import com.trufitrack.core.supabaseClient
import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.data.remote.dto.toConductor
import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.repository.AuthRepository
import io.github.jan.supabase.postgrest.from
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    override suspend fun login(correo: String, contrasena: String): Result<Conductor?> {
        return try {
            val dto = supabaseClient.from("conductor").select {
                filter {
                    eq("correo", correo)
                }
            }.decodeSingleOrNull<ConductorDto>()

            if (dto != null && BCrypt.checkpw(contrasena, dto.contrasena)) {
                Result.success(dto.toConductor())
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(conductor: Conductor, contrasena: String): Result<Unit> {
        return try {
            val hash = BCrypt.hashpw(contrasena, BCrypt.gensalt())
            val dto = ConductorDto(
                nombreCompleto = conductor.nombreCompleto,
                celular        = conductor.celular,
                licencia       = conductor.licencia,
                correo         = conductor.correo,
                contrasena     = hash
            )
            supabaseClient.from("conductor").insert(dto)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logout() {}

    override fun isUserLoggedIn(): Boolean = false
}
