package br.com.fiap.aura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.aura.Components.AuthTextField
import br.com.fiap.aura.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = viewModel(),
    onLoginSuccess: (token: String) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    val loginResult by viewModel.loginResult.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
            .padding(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Login", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = username,
                onValueChange = { username = it },
                label = "Usuário"
            )
            Spacer(modifier = Modifier.height(8.dp))

            AuthTextField(
                value = password,
                onValueChange = { password = it },
                label = "Senha",
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.login(username, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Entrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            loginResult?.let { result ->
                if (result.startsWith("ey")) { // token JWT
                    LaunchedEffect(result) {
                        onLoginSuccess(result)
                    }
                } else if (result.startsWith("Erro")) {
                    Text(result, color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onNavigateToRegister) {
                Text("Não tem conta? Cadastre-se", color = Color.White)
            }
        }
    }
}
