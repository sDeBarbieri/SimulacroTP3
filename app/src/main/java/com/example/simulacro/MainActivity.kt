package com.example.simulacro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacro.pages.FavsPage
import com.example.simulacro.pages.HomePage
import com.example.simulacro.pages.LoginPage
import com.example.simulacro.ui.theme.SimulacroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // 1. REQUERIDO para que Hilt funcione
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimulacroTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        composable("home") {
            // 2. HomePage ahora obtiene sus propios datos vía HiltViewModel,
            // solo necesita saber a dónde navegar.
            HomePage(
                onNavigateToFavs = {
                    navController.navigate("favs")
                }
            )
        }

        composable("favs") {
            // 3. Nota: Para que FavsPage funcione con los mismos datos,
            // debería usar el mismo ViewModel o Room.
            // Por ahora, lo dejamos simple para que compile.
            FavsPage(
                favoritePhrases = emptyList(), // Temporalmente vacío hasta conectar Room
                onToggleFavorite = { /* Se implementará con ViewModel */ },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}