package com.example.simulacro.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("quote") val quote: String,
    @SerializedName("author") val author: String
)