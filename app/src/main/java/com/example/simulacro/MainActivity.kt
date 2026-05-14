package com.example.simulacro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simulacro.model.dummyPhrases
import com.example.simulacro.pages.FavsPage
import com.example.simulacro.pages.HomePage
import com.example.simulacro.pages.LoginPage
import com.example.simulacro.ui.theme.SimulacroTheme

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
    var phrases by remember { mutableStateOf(dummyPhrases) }


    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomePage(
                phrases = phrases,
                onToggleFavorite = { clickedPhrase ->
                    phrases = phrases.map {
                        if (it.id == clickedPhrase.id) it.copy(isFavorite = !it.isFavorite) else it
                    }
                },
                onNavigateToFavs = {
                    navController.navigate("favs")
                }
            )
        }
        composable("favs") {
            FavsPage(
                favoritePhrases = phrases.filter { it.isFavorite },
                onToggleFavorite = { clickedPhrase ->
                    phrases = phrases.map {
                        if (it.id == clickedPhrase.id) it.copy(isFavorite = !it.isFavorite) else it
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
