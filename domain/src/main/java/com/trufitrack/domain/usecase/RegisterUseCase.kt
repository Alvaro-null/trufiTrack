package com.trufitrack.domain.usecase

import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(conductor: ConductorDto): Result<Unit> {
        return authRepository.register(conductor)
    }
}