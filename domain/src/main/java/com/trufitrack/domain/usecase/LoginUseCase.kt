package com.trufitrack.domain.usecase

import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(correo: String, contrasena: String): Result<ConductorDto?> {
        return authRepository.login(correo, contrasena)
    }
}