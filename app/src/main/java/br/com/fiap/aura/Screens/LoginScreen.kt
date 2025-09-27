package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
// Remover imports desnecessários de Row, width, size se AppBrandingFooter for o único uso
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
// Remover Icons.Default.SelfImprovement se não for usado em outro lugar aqui
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember // Para os estados de erro
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.stringResource // Para stringResources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import br.com.fiap.aura.R

// Cores podem ser movidas para um arquivo central de Tema/Cores
// É melhor que estas cores venham do seu MaterialTheme.colorScheme
private val ScreenBackgroundColor = Color(0xFF101010)
private val PrimaryAppColor = Color(0xFF56A8C2) // Usado nos ícones, botão, links. Pode vir do tema.
private val TextPrimaryColor = Color.White    // Usado nos textos, pode vir do tema.

@Composable
fun LoginScreen(
    onLoginClick: (email: String, senha: String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateBack: () -> Unit // Opcional
) {
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var senhaVisivel by rememberSaveable { mutableStateOf(false) }

    // Estados para validação (exemplo)
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackgroundColor) // Use MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Login", // stringResource(R.string.login_title),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = TextPrimaryColor, // MaterialTheme.colorScheme.onBackground
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                AuthTextField( // Usando o componente de CommonAuthComposables.kt
                    value = email,
                    onValueChange = {
                        email = it
                        // Lógica de validação do email aqui, ex:
                        // emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()) "Email inválido" else null
                    },
                    label = "Email", // stringResource(R.string.email_label)
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email", tint = PrimaryAppColor) },
                    keyboardType = KeyboardType.Email,
                    isError = emailError != null,
                    supportingText = emailError?.let { { Text(it) } }
                )
                Spacer(modifier = Modifier.height(16.dp))

                AuthTextField( // Usando o componente de CommonAuthComposables.kt
                    value = senha,
                    onValueChange = {
                        senha = it
                        // Lógica de validação da senha aqui, ex:
                        // senhaError = if (it.length < 6) "Senha muito curta" else null
                    },
                    label = "Senha", // stringResource(R.string.password_label)
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Senha", tint = PrimaryAppColor) },
                    visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (senhaVisivel) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                        val description = if (senhaVisivel) "Esconder senha" else "Mostrar senha"
                        IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                            Icon(imageVector = image, contentDescription = description, tint = PrimaryAppColor)
                        }
                    },
                    keyboardType = KeyboardType.Password,
                    isError = senhaError != null,
                    supportingText = senhaError?.let { { Text(it) } }
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextButton(
                    onClick = { /* TODO: Implementar lógica de esqueceu a senha */ },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Esqueceu a senha?", // stringResource(R.string.forgot_password_link),
                        color = PrimaryAppColor // Use MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // Implementar validação robusta aqui
                        // Ex: emailError = if (!isValidEmail(email)) "Email inválido" else null
                        //     senhaError = if (senha.isBlank()) "Senha obrigatória" else null
                        // if (emailError == null && senhaError == null) {
                        onLoginClick(email, senha)
                        // }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryAppColor) // Use MaterialTheme.colorScheme.primary
                ) {
                    Text(
                        text = "Entrar", // stringResource(R.string.login_button),
                        color = TextPrimaryColor, // Use cor apropriada para texto em botão primário (e.g., MaterialTheme.colorScheme.onPrimary)
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = onNavigateToRegister) {
                    Text(
                        text = "Não tem conta? Cadastre-se", // stringResource(R.string.no_account_register_link),
                        color = PrimaryAppColor // Use MaterialTheme.colorScheme.primary
                    )
                }
            }

            AppBrandingFooter() // Usando o componente de CommonAuthComposables.kt
        }
    }
}

// REMOVER AS DEFINIÇÕES PRIVADAS DE AuthTextField e AppBrandingFooter DESTE ARQUIVO,
// SE ELAS EXISTIAM ANTERIORMENTE. Elas devem vir de CommonAuthComposables.kt

@Preview(showBackground = true, backgroundColor = 0xFF101010)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onLoginClick = { _, _ -> },
            onNavigateToRegister = {},
            onNavigateBack = {}
        )
    }
}

