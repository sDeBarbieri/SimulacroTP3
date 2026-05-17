package com.example.simulacro.services

import com.example.simulacro.model.QuoteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface QuoteApiService {
    @GET("v1/quotes")
    suspend fun getQuotes(
        @Header("X-Api-Key") apiKey: String,
        @Query("category") category: String? = null
    ): Response<List<QuoteResponse>>
}