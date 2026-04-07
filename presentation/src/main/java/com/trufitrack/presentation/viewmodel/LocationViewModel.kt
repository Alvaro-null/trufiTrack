package com.trufitrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trufitrack.data.remote.dto.LocationDto
import com.trufitrack.domain.usecase.GetLatestLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLatestLocationUseCase: GetLatestLocationUseCase
) : ViewModel() {

    private val _location = MutableStateFlow<LocationDto?>(null)
    val location: StateFlow<LocationDto?> = _location

    fun loadLatestLocation(trufiId: Int) {
        viewModelScope.launch {
            try {
                val latestLocation = getLatestLocationUseCase(trufiId)
                _location.value = latestLocation
            } catch (e: Exception) {
                // Manejar error
            }
        }
    }
}