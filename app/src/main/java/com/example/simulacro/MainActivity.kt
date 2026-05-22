package com.example.simulacro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacro.entity.toPhrase
import com.example.simulacro.pages.FavsPage
import com.example.simulacro.pages.HomePage
import com.example.simulacro.pages.LoginPage
import com.example.simulacro.pages.PhraseViewModel
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
    // Inyectamos el ViewModel usando hiltViewModel()
    // Al estar fuera de las rutas, podemos compartirlo o pedirlo en cada ruta
    val viewModel: PhraseViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        composable("home") {
            LaunchedEffect(Unit) { viewModel.loadPhrases() }

            HomePage(
                phrases = viewModel.phrases, // Lista que viene de Retrofit
                onFavoriteClick = { phrase -> viewModel.toggleFavorite(phrase) },
                onNavigateToFavs = { navController.navigate("favs") }
            )
        }

        composable("favs") {
            // Recolectamos el Flow de Room como un Estado de Compose
            val favoriteList by viewModel.favorites.collectAsState()

            FavsPage(
                favoritePhrases = favoriteList,
                onToggleFavorite = { entity ->
                    viewModel.toggleFavorite(entity.toPhrase())
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}