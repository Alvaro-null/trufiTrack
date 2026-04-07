package com.trufitrack.domain.usecase

import com.trufitrack.data.remote.dto.LocationDto
import com.trufitrack.domain.repository.LocationRepository
import javax.inject.Inject

class GetLatestLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(trufiId: Int): LocationDto? {
        return locationRepository.getLatestLocation(trufiId)
    }
}