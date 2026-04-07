package com.trufitrack.domain.usecase

import com.trufitrack.data.remote.dto.LocationDto
import com.trufitrack.domain.repository.LocationRepository
import javax.inject.Inject

class PostLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(location: LocationDto) {
        locationRepository.postLocation(location)
    }
}