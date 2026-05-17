package com.example.simulacro.pages

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState // Agregado
import androidx.compose.runtime.getValue         // Agregado
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel // El import clave
import com.example.simulacro.components.PhraseItem
import com.example.simulacro.model.Phrase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    viewModel: QuoteViewModel = hiltViewModel(), // Ahora debería reconocerlo
    onNavigateToFavs: () -> Unit
) {
    // Obtenemos la lista del ViewModel y la convertimos en un estado de Compose
    val phrases by viewModel.phrases.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home - Frases") },
                actions = {
                    TextButton(onClick = onNavigateToFavs) {
                        Text("Ver Favoritos")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.fetchNewQuote() }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(phrases) { phrase ->
                PhraseItem(
                    phrase = phrase,
                    onToggleFavorite = {
                        // Aquí llamarías a la función de favoritos del ViewModel cuando la crees
                        Log.d("HOME", "Click en favorito de: ${phrase.text}")
                    }
                )
            }
        }
    }
}