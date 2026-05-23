package com.example.simulacro.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// repository/AuthRepository.kt
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun getCurrentUser() = firebaseAuth.currentUser

    suspend fun login(email: String, pass: String): Result<AuthResult> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() = firebaseAuth.signOut()
}