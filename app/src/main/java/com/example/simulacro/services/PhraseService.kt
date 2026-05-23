package com.example.simulacro.services

import com.example.simulacro.model.PhraseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PhraseApiService {
    @GET("v1/quotes")
    suspend fun getPhrases(
        @Header("X-Api-Key") apiKey: String,
        @Query("category") category: String? = null
    ): Response<List<PhraseResponse>>
}