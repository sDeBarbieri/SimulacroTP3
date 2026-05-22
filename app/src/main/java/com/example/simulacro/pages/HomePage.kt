package com.example.simulacro.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
    phrases: List<Phrase>,
    onFavoriteClick: (Phrase) -> Unit,
    onNavigateToFavs: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToFavs) {
                Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(phrases) { phrase ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = phrase.text, style = MaterialTheme.typography.bodyLarge)
                            Text(text = "- ${phrase.author}", style = MaterialTheme.typography.labelMedium)
                        }
                        IconButton(onClick = { onFavoriteClick(phrase) }) {
                            Icon(Icons.Default.FavoriteBorder, contentDescription = "Añadir")
                        }
                    }
                }
            }
        }
    }
}