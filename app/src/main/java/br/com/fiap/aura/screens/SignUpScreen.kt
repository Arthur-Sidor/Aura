package br.com.fiap.aura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import br.com.fiap.aura.model.auth.RegisterRequestModel
import br.com.fiap.aura.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    viewModel: AuthViewModel = viewModel(),
    onRegisterSuccess: (token: String) -> Unit,
    onNavigateBack: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    val registerResult by viewModel.registerResult.collectAsState()

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
            Text("Criar Conta", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            AuthTextField(
                value = nome,
                onValueChange = { nome = it },
                label = "Nome Completo",
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) }
            )
            Spacer(modifier = Modifier.height(8.dp))

            AuthTextField(
                value = email,
                onValueChange = { email = it },
                label = "Email"
            )
            Spacer(modifier = Modifier.height(8.dp))

            AuthTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = "CPF"
            )
            Spacer(modifier = Modifier.height(8.dp))

            AuthTextField(
                value = senha,
                onValueChange = { senha = it },
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
                onClick = { viewModel.register(
                    RegisterRequestModel(
                        email = email,
                        password = senha,
                        confirmPassword = senha,
                        username = nome,
                        cpf = cpf
                    )
                ) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cadastrar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            registerResult?.let { result ->
                if (!result.startsWith("Erro")) {
                    LaunchedEffect(result) {
                        onRegisterSuccess(result)
                    }
                } else {
                    Text(result, color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = onNavigateBack) {
                Text("Voltar", color = Color.White)
            }
        }
    }
}
