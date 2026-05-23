package com.example.simulacro.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginPage(
    // Inyectamos el ViewModel de Hilt
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bienvenido", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))
        // Email
        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Password
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        viewModel.errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { viewModel.onLoginClick(onLoginSuccess) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.isLoading // Deshabilitar si está cargando
        ) {
            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            } else {
                Text("Iniciar Sesión")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { /* Implement registration */ }) {
            Text("Registro")
        }
    }
}