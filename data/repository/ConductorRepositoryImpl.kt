// data/repository/ConductorRepositoryImpl.kt
package com.trufitrack.data.repository

import com.trufitrack.core.supabaseClient
import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.repository.ConductorRepository
import io.github.jan.supabase.postgrest.postgrest
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class ConductorRepositoryImpl @Inject constructor() : ConductorRepository {

    override suspend fun registrarConductor(conductor: Conductor, contrasena: String): Result<Unit> {
        return try {
            val hash = BCrypt.hashpw(contrasena, BCrypt.gensalt())

            val dto = ConductorDto(
                nombreCompleto = conductor.nombreCompleto,
                celular        = conductor.celular,
                licencia       = conductor.licencia,
                correo         = conductor.correo,
                contrasena     = hash
            )

            supabaseClient.postgrest["conductor"].insert(dto)
            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}