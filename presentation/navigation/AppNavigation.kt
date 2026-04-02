// presentation/navigation/AppNavigation.kt
package com.trufitrack.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trufitrack.presentation.registro.RegistroScreen

object Routes {
    const val REGISTRO = "registro"
    // Aquí irás agregando más rutas:
    // const val LOGIN   = "login"
    // const val MAPA    = "mapa"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.REGISTRO
    ) {
        composable(Routes.REGISTRO) {
            RegistroScreen(
                onRegistroExitoso = {
                    // navController.navigate(Routes.LOGIN)
                }
            )
        }
    }
}