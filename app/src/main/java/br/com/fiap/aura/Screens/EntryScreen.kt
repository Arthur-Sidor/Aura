package br.com.fiap.aura.Screens // Certifique-se que o package está correto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
// import androidx.compose.ui.res.stringResource // Descomente se usar resources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import br.com.fiap.aura.R // Descomente se usar resources

// Cores (Idealmente, mova para seu Theme.kt)
private val ScreenBackgroundColor = Color(0xFF101010)
private val PrimaryAppColor = Color(0xFF56A8C2)
private val TextPrimaryColor = Color.White
private val TextSecondaryColor = Color.Gray

// RENOMEADO para EntryScreen
@Composable
fun EntryScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit // Mantive "Register" para consistência, poderia ser "Cadastro"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            AppBranding() // Renomeei AppLogoAndName para algo mais genérico

            Spacer(modifier = Modifier.height(48.dp))

            ActionButton( // Renomeei AuthButton para algo mais genérico
                // text = stringResource(R.string.login_button_text),
                text = "Login",
                onClick = onNavigateToLogin
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                // text = stringResource(R.string.register_button_text),
                text = "Cadastro",
                onClick = onNavigateToRegister,
                isPrimary = false
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun AppBranding() { // Era AppLogoAndName
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Default.SelfImprovement,
            contentDescription = "Ícone do app", // stringResource(R.string.app_icon_description),
            tint = PrimaryAppColor,
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            // text = stringResource(R.string.app_name_softmind),
            text = "SoftMind",
            textAlign = TextAlign.Center,
            color = TextPrimaryColor,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            // text = stringResource(R.string.app_subtitle_entry), // Novo resource se necessário
            text = "Acesse sua conta ou crie uma nova.",
            color = TextSecondaryColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun ActionButton( // Era AuthButton
    text: String,
    onClick: () -> Unit,
    isPrimary: Boolean = true
) {
    val buttonModifier = Modifier.fillMaxWidth()
    if (isPrimary) {
        Button(
            onClick = onClick,
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryAppColor)
        ) {
            Text(text, color = TextPrimaryColor, fontSize = 16.sp)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = buttonModifier,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryAppColor)
        ) {
            Text(text, color = PrimaryAppColor, fontSize = 16.sp)
        }
    }
}

// RENOMEADO para EntryScreenPreview
@Preview(showBackground = true, backgroundColor = 0xFF101010)
@Composable
fun EntryScreenPreview() {
    // Para o preview funcionar bem com MaterialTheme,
    // envolva com o tema da sua aplicação se ele definir typography etc.
    // Ex: YourAppTheme {
    EntryScreen(
        onNavigateToLogin = {},
        onNavigateToRegister = {}
    )
    // }
}

