// domain/usecase/RegistrarConductorUseCase.kt
package com.trufitrack.domain.usecase

import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.repository.ConductorRepository
import javax.inject.Inject

class RegistrarConductorUseCase @Inject constructor(
    private val repository: ConductorRepository
) {
    suspend operator fun invoke(conductor: Conductor, contrasena: String): Result<Unit> {
        // Validaciones de negocio
        if (conductor.nombreCompleto.isBlank()) return Result.failure(Exception("El nombre es requerido"))
        if (conductor.correo.isBlank()) return Result.failure(Exception("El correo es requerido"))
        if (conductor.licencia.isBlank()) return Result.failure(Exception("La licencia es requerida"))
        if (contrasena.length < 6) return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))

        return repository.registrarConductor(conductor, contrasena)
    }
}