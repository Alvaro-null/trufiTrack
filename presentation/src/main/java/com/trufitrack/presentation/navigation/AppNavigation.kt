package com.trufitrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trufitrack.presentation.login.LoginScreen
import com.trufitrack.presentation.registro.RegistroScreen

object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val MAPA = "mapa"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginExitoso = {
                    navController.navigate(Routes.MAPA) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onIrARegistro = {
                    navController.navigate(Routes.REGISTRO)
                }
            )
        }
        composable(Routes.REGISTRO) {
            RegistroScreen(
                onRegistroExitoso = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTRO) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.MAPA) {
            // Placeholder for map screen
            // MapScreen()
        }
    }
}
