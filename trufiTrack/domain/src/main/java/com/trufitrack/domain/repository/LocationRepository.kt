package com.trufitrack.domain.repository

import com.trufitrack.data.remote.dto.LocationDto

interface LocationRepository {
    suspend fun postLocation(location: LocationDto)
    suspend fun getLatestLocation(trufiId: Int): LocationDto?
}