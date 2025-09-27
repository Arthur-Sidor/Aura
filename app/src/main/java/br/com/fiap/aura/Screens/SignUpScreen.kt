package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email // Exemplo de ícone
import androidx.compose.material.icons.filled.Lock // Exemplo de ícone
import androidx.compose.material.icons.filled.Person // Exemplo de ícone
import androidx.compose.material.icons.filled.SelfImprovement // Logo
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import br.com.fiap.aura.R

// Cores (Consistentes com as outras telas)
private val ScreenBackgroundColor = Color(0xFF101010)
private val PrimaryAppColor = Color(0xFF56A8C2)
private val TextPrimaryColor = Color.White
private val TextSecondaryColor = Color.Gray
private val TextFieldBackgroundColor = Color(0xFF1C1C1C) // Cor de fundo para os campos


@Composable
fun SignUpScreen(
    onRegisterClick: (nome: String, email: String, cpf: String, senha: String) -> Unit,
    onNavigateBack: () -> Unit // Para voltar, caso haja um botão de voltar
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var senhaVisivel by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(rememberScrollState()), // Permite rolagem se o conteúdo for maior
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Empurra o rodapé para baixo
        ) {
            // Seção Superior: Título e Campos
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    // text = stringResource(R.string.signup_title),
                    text = "Criar Conta",
                    style = MaterialTheme.typography.headlineMedium.copy(color = TextPrimaryColor, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Campo Nome
                AuthTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = "Nome Completo", // stringResource(R.string.name_label)
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Nome", tint = PrimaryAppColor) }
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Campo Email
                AuthTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email", // stringResource(R.string.email_label)
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email", tint = PrimaryAppColor) },
                    keyboardType = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Campo CPF
                AuthTextField(
                    value = cpf,
                    onValueChange = {
                        // Limitar e formatar CPF se necessário
                        // Ex: if (it.length <= 11 && it.all { char -> char.isDigit() }) cpf = it
                        cpf = it
                    },
                    label = "CPF", // stringResource(R.string.cpf_label)
                    // Adicionar ícone para CPF se desejado
                    keyboardType = KeyboardType.Number
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Campo Senha
                AuthTextField(
                    value = senha,
                    onValueChange = { senha = it },
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
                    keyboardType = KeyboardType.Password
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Botão Cadastrar
                Button(
                    onClick = {
                        // Adicionar validação aqui antes de chamar onRegisterClick
                        onRegisterClick(nome, email, cpf, senha)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryAppColor)
                ) {
                    Text(
                        // text = stringResource(R.string.register_button),
                        text = "Cadastrar",
                        color = TextPrimaryColor,
                        fontSize = 16.sp
                    )
                }
            } // Fim da Seção Superior

            // Rodapé
//            AppBrandingFooter()

        } // Fim da Coluna principal
    } // Fim do Box principal
}

@Composable
private fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = TextSecondaryColor) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAppColor,
            unfocusedBorderColor = TextSecondaryColor,
            focusedTextColor = TextPrimaryColor,
            unfocusedTextColor = TextPrimaryColor,
            cursorColor = PrimaryAppColor,
            focusedContainerColor = TextFieldBackgroundColor, // Fundo quando focado
            unfocusedContainerColor = TextFieldBackgroundColor, // Fundo quando não focado
            disabledContainerColor = TextFieldBackgroundColor
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF101010)
@Composable
fun SignUpScreenPreview() {
    // Para o preview funcionar bem com MaterialTheme,
    // envolva com o tema da sua aplicação se ele definir typography etc.
    // Ex: YourAppTheme { SignUpScreen(onRegisterClick = {_,_,_,_ -> }, onNavigateBack = {}) }
    MaterialTheme { // Adicionando MaterialTheme para estilos de tipografia
        SignUpScreen(
            onRegisterClick = { _, _, _, _ -> },
            onNavigateBack = {}
        )
    }
}
