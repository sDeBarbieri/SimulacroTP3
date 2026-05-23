package com.example.simulacro.pages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simulacro.entity.PhraseEntity
import com.example.simulacro.entity.toEntity
import com.example.simulacro.entity.toPhrase
import com.example.simulacro.model.Phrase
import com.example.simulacro.repository.PhraseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.emptyList

@HiltViewModel
class PhraseViewModel @Inject constructor(
    private val repository: PhraseRepository
) : ViewModel() {

    // Estado para la lista de la API
    var phrases by mutableStateOf<List<Phrase>>(emptyList())

    // Estado reactivo de favoritos (Room)
    val favorites = repository.getFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun loadPhrases() {
        viewModelScope.launch {
            try {
                // El repositorio ya me da objetos 'Phrase' listos
                val result = repository.getPhrasesFromApi()
                if (result.isNotEmpty()) {
                    phrases = result
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(phrase: Phrase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavorite(phrase.toEntity())
        }
    }

    // Sobrecarga para cuando borramos desde la pantalla de favoritos
    fun toggleFavorite(entity: PhraseEntity) {
        toggleFavorite(entity.toPhrase())
    }
}