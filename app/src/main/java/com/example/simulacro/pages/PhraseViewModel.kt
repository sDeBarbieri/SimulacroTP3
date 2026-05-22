package com.example.simulacro.pages

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simulacro.entity.PhraseEntity
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
                val response = repository.getPhrasesFromApi()

                if (response.isSuccessful) {
                    // Obtenemos la lista de la respuesta (si es nula, usamos lista vacía)
                    val quoteResponses = response.body() ?: emptyList()

                    // MAPEAMOS: Convertimos QuoteResponse a Phrase
                    phrases = quoteResponses.map { quote ->
                        Phrase(
                            id = quote.quote.hashCode(),
                            text = quote.quote,    // O el nombre que tenga el texto en QuoteResponse
                            author = quote.author
                        )
                    }
                } else {
                    // Opcional: Manejar error de código (404, 500, etc.)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorite(phrase: Phrase) {
        viewModelScope.launch(Dispatchers.IO) { // Ejecutar en hilo de base de datos
            try {
                // Convertimos el modelo Phrase a PhraseEntity para Room
                val entity = PhraseEntity(
                    id = phrase.id,
                    text = phrase.text,
                    author = phrase.author
                )
                repository.toggleFavorite(entity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Sobrecarga para cuando borramos desde la pantalla de favoritos
    fun toggleFavorite(entity: PhraseEntity) {
        val phrase = Phrase(
            id = entity.id,
            text = entity.text,
            author = entity.author
        )
        toggleFavorite(phrase) // Llama a la función de arriba que tiene el launch
    }
}