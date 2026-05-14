package com.example.simulacro.pages

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simulacro.components.PhraseItem
import com.example.simulacro.model.Phrase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavsPage(
    favoritePhrases: List<Phrase>,
    onToggleFavorite: (Phrase) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Favoritos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (favoritePhrases.isEmpty()) {
            Text(
                text = "No tienes frases favoritas aún.",
                modifier = Modifier.padding(innerPadding).padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
            ) {
                items(favoritePhrases) { phrase ->
                    PhraseItem(phrase = phrase, onToggleFavorite = onToggleFavorite)
                }
            }
        }
    }
}