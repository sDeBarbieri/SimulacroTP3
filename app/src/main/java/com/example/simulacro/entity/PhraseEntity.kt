package com.example.simulacro.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simulacro.model.Phrase

@Entity(tableName = "favorite_phrases")
data class PhraseEntity(
    @PrimaryKey val id: Int, // El ID que viene de la API
    val text: String,
    val author: String
)
fun PhraseEntity.toPhrase(): Phrase {
    return Phrase(
        id = this.id,
        text = this.text,
        author = this.author
    )
}