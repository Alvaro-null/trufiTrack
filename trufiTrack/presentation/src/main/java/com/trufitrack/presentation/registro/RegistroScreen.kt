package com.trufitrack.presentation.registro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit,
    onVolver: () -> Unit,
    viewModel: RegistroViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.registroExitoso) {
        if (uiState.registroExitoso) onRegistroExitoso()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de conductor") },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Ingresa tus datos para crear tu cuenta",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = uiState.nombreCompleto,
                onValueChange = viewModel::onNombreChange,
                label = { Text("Nombre completo *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.licencia,
                onValueChange = viewModel::onLicenciaChange,
                label = { Text("Número de licencia *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.correo,
                onValueChange = viewModel::onCorreoChange,
                label = { Text("Correo electrónico *") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.celular,
                onValueChange = viewModel::onCelularChange,
                label = { Text("Celular") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.contrasena,
                onValueChange = viewModel::onContrasenaChange,
                label = { Text("Contraseña *") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                supportingText = { Text("Mínimo 6 caracteres") }
            )

            OutlinedTextField(
                value = uiState.confirmarContrasena,
                onValueChange = viewModel::onConfirmarContrasenaChange,
                label = { Text("Confirmar contraseña *") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                isError = uiState.confirmarContrasena.isNotEmpty() &&
                          uiState.contrasena != uiState.confirmarContrasena
            )

            uiState.error?.let { mensaje ->
                Text(
                    text = mensaje,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = viewModel::registrar,
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isLoading &&
                          uiState.nombreCompleto.isNotBlank() &&
                          uiState.licencia.isNotBlank() &&
                          uiState.correo.isNotBlank() &&
                          uiState.contrasena.isNotBlank() &&
                          uiState.confirmarContrasena.isNotBlank()
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Registrarse")
                }
            }

            TextButton(
                onClick = onVolver,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
