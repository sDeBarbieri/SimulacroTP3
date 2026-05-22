package com.example.simulacro.repository

import com.example.simulacro.model.QuoteResponse
import com.example.simulacro.services.QuoteApiService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val apiService: QuoteApiService
) {
    private val API_KEY = "IfimhkW7lhtFwtvWy8cXQmhQDo2CvuW9cg3dfAIa"

    suspend fun getRandomQuote(): QuoteResponse? {
        val response = apiService.getQuotes(API_KEY)
        return if (response.isSuccessful) {
            response.body()?.firstOrNull()
        } else {
            null
        }
    }
}