package com.trufitrack.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trufitrack.data.remote.dto.ConductorDto
import com.trufitrack.domain.usecase.IsLoggedInUseCase
import com.trufitrack.domain.usecase.LoginUseCase
import com.trufitrack.domain.usecase.LogoutUseCase
import com.trufitrack.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    private val _conductor = MutableStateFlow<ConductorDto?>(null)
    val conductor: StateFlow<ConductorDto?> = _conductor

    init {
        checkLoginStatus()
    }

    fun login(correo: String, contrasena: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = loginUseCase(correo, contrasena)
            if (result.isSuccess) {
                val conductor = result.getOrNull()
                if (conductor != null) {
                    _conductor.value = conductor
                    _authState.value = AuthState.Success
                } else {
                    _authState.value = AuthState.Error("Credenciales incorrectas")
                }
            } else {
                _authState.value = AuthState.Error(result.exceptionOrNull()?.message ?: "Error")
            }
        }
    }

    fun register(conductor: ConductorDto) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = registerUseCase(conductor)
            _authState.value = if (result.isSuccess) AuthState.Success else AuthState.Error(result.exceptionOrNull()?.message ?: "Error")
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _authState.value = AuthState.Idle
            _conductor.value = null
        }
    }

    private fun checkLoginStatus() {
        if (isLoggedInUseCase()) {
            _authState.value = AuthState.Success
        }
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        object Success : AuthState()
        data class Error(val message: String) : AuthState()
    }
}