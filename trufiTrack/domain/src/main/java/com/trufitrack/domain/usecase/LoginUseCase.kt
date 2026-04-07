package com.trufitrack.domain.usecase

import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(correo: String, contrasena: String): Result<Conductor?> {
        return authRepository.login(correo, contrasena)
    }
}
