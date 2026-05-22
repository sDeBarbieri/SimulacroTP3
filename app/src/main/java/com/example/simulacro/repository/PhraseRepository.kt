package com.example.simulacro.repository

import com.example.simulacro.data.FavoriteDao
import com.example.simulacro.entity.PhraseEntity
import com.example.simulacro.services.QuoteApiService
import javax.inject.Inject

class PhraseRepository @Inject constructor(
    private val api: QuoteApiService,
    private val dao: FavoriteDao
) {
    private val API_KEY = "IfimhkW7lhtFwtvWy8cXQmhQDo2CvuW9cg3dfAIa"
    // Desde API
    suspend fun getPhrasesFromApi() = api.getQuotes(API_KEY)

    // Desde Room
    fun getFavorites() = dao.getAllFavorites()

    suspend fun toggleFavorite(phrase: PhraseEntity) {
        if (dao.isFavorite(phrase.id)) {
            dao.deleteFavorite(phrase)
        } else {
            dao.insertFavorite(phrase)
        }
    }
}