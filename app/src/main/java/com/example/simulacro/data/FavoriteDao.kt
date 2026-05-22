package com.example.simulacro.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simulacro.entity.PhraseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_phrases")
    fun getAllFavorites(): Flow<List<PhraseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(phrase: PhraseEntity)

    @Delete
    suspend fun deleteFavorite(phrase: PhraseEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_phrases WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean
}