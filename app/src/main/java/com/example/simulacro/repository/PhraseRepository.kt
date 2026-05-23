package com.example.simulacro.repository

import com.example.simulacro.data.FavoriteDao
import com.example.simulacro.entity.PhraseEntity
import com.example.simulacro.model.Phrase
import com.example.simulacro.model.toDomain
import com.example.simulacro.services.PhraseApiService
import javax.inject.Inject

class PhraseRepository @Inject constructor(
    private val api: PhraseApiService,
    private val dao: FavoriteDao
) {
    private val API_KEY = "IfimhkW7lhtFwtvWy8cXQmhQDo2CvuW9cg3dfAIa"
    // Desde API
    suspend fun getPhrasesFromApi(): List<Phrase> {
        val response = api.getPhrases(API_KEY)
        return if (response.isSuccessful) {
            // Mapeamos aquí mismo
            response.body()?.map { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }

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