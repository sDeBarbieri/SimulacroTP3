package com.example.simulacro.model

import com.google.gson.annotations.SerializedName

data class PhraseResponse(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)

fun PhraseResponse.toDomain(): Phrase {
    return Phrase(
        id = this.quote.hashCode(),
        text = this.quote,
        author = this.author
    )
}