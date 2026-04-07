package com.trufitrack.presentation.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trufitrack.domain.model.Conductor
import com.trufitrack.domain.usecase.RegistrarConductorUseCase
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
    val confirmarContrasena: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val registroExitoso: Boolean = false
)

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val registrarConductorUseCase: RegistrarConductorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistroUiState())
    val uiState: StateFlow<RegistroUiState> = _uiState.asStateFlow()

    fun onNombreChange(value: String)            { _uiState.value = _uiState.value.copy(nombreCompleto = value, error = null) }
    fun onCelularChange(value: String)           { _uiState.value = _uiState.value.copy(celular = value, error = null) }
    fun onLicenciaChange(value: String)          { _uiState.value = _uiState.value.copy(licencia = value, error = null) }
    fun onCorreoChange(value: String)            { _uiState.value = _uiState.value.copy(correo = value, error = null) }
    fun onContrasenaChange(value: String)        { _uiState.value = _uiState.value.copy(contrasena = value, error = null) }
    fun onConfirmarContrasenaChange(value: String) { _uiState.value = _uiState.value.copy(confirmarContrasena = value, error = null) }

    fun registrar() {
        val state = _uiState.value

        if (state.contrasena != state.confirmarContrasena) {
            _uiState.value = state.copy(error = "Las contraseñas no coinciden")
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, error = null)

            val conductor = Conductor(
                nombreCompleto = state.nombreCompleto,
                celular        = state.celular.ifBlank { null },
                licencia       = state.licencia,
                correo         = state.correo
            )

            val result = registrarConductorUseCase(conductor, state.contrasena)

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
