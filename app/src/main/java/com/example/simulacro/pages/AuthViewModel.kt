package com.example.simulacro.pages

import androidx.activity.result.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simulacro.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun onLoginClick(onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            val result = repository.login(email, password)
            if (result.isSuccess) {
                onSuccess()
            } else {
                errorMessage = result.exceptionOrNull()?.message
            }
            isLoading = false
        }
    }
}