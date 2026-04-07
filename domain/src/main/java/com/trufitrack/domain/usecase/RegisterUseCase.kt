package com.trufitrack.domain.usecase

import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(conductor: Conductor, contrasena: String): Result<Unit> {
        return authRepository.register(conductor, contrasena)
    }
}
