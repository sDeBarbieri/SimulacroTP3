package com.example.simulacro.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simulacro.components.PhraseItem
import com.example.simulacro.model.Phrase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    phrases: List<Phrase>,
    onToggleFavorite: (Phrase) -> Unit,
    onNavigateToFavs: () -> Unit
) {
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
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(phrases) { phrase ->
                PhraseItem(phrase = phrase, onToggleFavorite = onToggleFavorite)
            }
        }
    }
}
