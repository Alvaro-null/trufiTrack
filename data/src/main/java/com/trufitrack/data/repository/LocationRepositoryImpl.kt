package com.trufitrack.data.repository

import com.trufitrack.core.supabaseClient
import com.trufitrack.data.remote.dto.LocationDto
import com.trufitrack.domain.repository.LocationRepository
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor() : LocationRepository {

    override suspend fun postLocation(location: LocationDto) {
        supabaseClient.postgrest["ubicacion"].insert(location)
    }

    override suspend fun getLatestLocation(trufiId: Int): LocationDto? {
        return supabaseClient.postgrest["ubicacion"]
            .select {
                eq("id_trufi", trufiId)
                order("fecha_hora", ascending = false)
                limit(1)
            }
            .decodeSingleOrNull<LocationDto>()
    }
}