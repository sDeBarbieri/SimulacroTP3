package com.example.simulacro.pages
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simulacro.model.Phrase
import com.example.simulacro.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private val _phrases = MutableStateFlow<List<Phrase>>(emptyList())
    val phrases: StateFlow<List<Phrase>> = _phrases

    fun fetchNewQuote() {
        viewModelScope.launch {
            Log.d("API_TEST", "Llamando a la API...")
            val result = repository.getRandomQuote()
            if (result != null) {
                Log.d("API_TEST", "Datos recibidos: Quote = ${result.quote}, Author = ${result.author}")

                // Ahora esto no dará error porque id tiene valor por defecto 0
                val newPhrase = Phrase(
                    text = result.quote,
                    author = result.author
                )

                // Forma más limpia de actualizar la lista en un StateFlow
                _phrases.value = _phrases.value + newPhrase
            } else {
                Log.e("API_TEST", "La API devolvió null o hubo un error")
            }
        }
    }

}