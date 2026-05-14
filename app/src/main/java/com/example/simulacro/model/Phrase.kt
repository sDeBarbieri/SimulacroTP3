package com.example.simulacro.model

data class Phrase(
    val id: Int,
    val text: String,
    val author: String,
    var isFavorite: Boolean = false
)

val dummyPhrases = listOf(
    Phrase(1, "La vida es lo que pasa mientras estás ocupado haciendo otros planes.", "John Lennon"),
    Phrase(2, "El único modo de hacer un gran trabajo es amar lo que haces.", "Steve Jobs"),
    Phrase(3, "No cuentes los días, haz que los días cuenten.", "Muhammad Ali"),
    Phrase(4, "La mente es todo. Lo que pienses, en eso te conviertes.", "Buda"),
    Phrase(5, "Sé el cambio que quieres ver en el mundo.", "Mahatma Gandhi"),
    Phrase(6, "La felicidad no es algo hecho. Viene de tus propias acciones.", "Dalai Lama"),
    Phrase(7, "Lo que no nos mata, nos hace más fuertes.", "Friedrich Nietzsche"),
    Phrase(8, "Pienso, luego existo.", "René Descartes"),
    Phrase(9, "Solo sé que no sé nada.", "Sócrates"),
    Phrase(10, "El éxito es aprender a ir de fracaso en fracaso sin desesperarse.", "Winston Churchill")
)