package com.trufitrack.presentation.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegistroUiState(
    val nombreCompleto: String = "",
    val celular: String = "",
    val licencia: String = "",
    val correo: String = "",
    val contrasena: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val registroExitoso: Boolean = false
)

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onNombreChange(value: String)    { _uiState.value = _uiState.value.copy(nombreCompleto = value) }
    fun onCelularChange(value: String)   { _uiState.value = _uiState.value.copy(celular = value) }
    fun onLicenciaChange(value: String)  { _uiState.value = _uiState.value.copy(licencia = value) }
    fun onCorreoChange(value: String)    { _uiState.value = _uiState.value.copy(correo = value) }
    fun onContrasenaChange(value: String){ _uiState.value = _uiState.value.copy(contrasena = value) }

    fun registrar() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            val conductor = ConductorDto(
                nombre_completo = _uiState.value.nombreCompleto,
                celular = _uiState.value.celular,
                licencia = _uiState.value.licencia,
                correo = _uiState.value.correo,
                contrasena = _uiState.value.contrasena
            )

            val result = registerUseCase(conductor)

            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(isLoading = false, registroExitoso = true)
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = result.exceptionOrNull()?.message ?: "Error desconocido"
                )
            }
        }
    }
}
