package com.example.simulacro.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simulacro.components.PhraseItem
import com.example.simulacro.model.Phrase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    phrases: List<Phrase>,
    favoriteIds: Set<Int>,
    onFavoriteClick: (Phrase) -> Unit,
    onNavigateToFavs: () -> Unit,
    onRefresh: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Frases del Día") },
                actions = {
                    // Botón de actualizar en la barra superior
                    IconButton(onClick = onRefresh) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Actualizar frase"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToFavs) {
                Icon(Icons.Default.Favorite, contentDescription = "Ver Favoritos")
            }
        }
    ) { padding ->
        if (phrases.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = onRefresh) {
                    Text("Cargar frase nueva")
                }
            }
        } else {
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 8.dp) // Espaciado lateral extra
        ) {
            items(phrases) { phrase ->
                // Usamos el componente PhraseItem que ya creaste
                PhraseItem(
                    phrase = phrase,
                    // Calculamos isFavorite en tiempo real con el Set de IDs
                    isFavorite = favoriteIds.contains(phrase.id),
                    onToggleFavorite = { onFavoriteClick(it) }
                )
                }
            }
        }

    }
}