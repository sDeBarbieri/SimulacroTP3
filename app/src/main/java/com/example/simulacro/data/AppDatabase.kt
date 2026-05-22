package com.example.simulacro.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simulacro.entity.PhraseEntity

@Database(entities = [PhraseEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}