package br.com.fiap.aura.Screens // Ou o pacote de sua escolha para componentes comuns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// Defina as cores aqui ou importe-as de um arquivo central de Cores do seu tema
// É melhor que estas cores venham do seu MaterialTheme.colorScheme se possível
private val PrimaryAppColor = Color(0xFF56A8C2) // Exemplo, idealmente do tema
private val TextPrimaryColor = Color.White      // Exemplo, idealmente do tema
private val TextSecondaryColor = Color.Gray     // Exemplo, idealmente do tema
private val TextFieldBackgroundColor = Color(0xFF1C1C1C) // Exemplo

@Composable
fun AuthTextField( // Removido 'private' para ser acessível de outros arquivos
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = TextSecondaryColor) }, // Use cores do tema aqui
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryAppColor, // Use MaterialTheme.colorScheme.primary
            unfocusedBorderColor = if (isError) MaterialTheme.colorScheme.error else TextSecondaryColor, // MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
            focusedTextColor = TextPrimaryColor, // MaterialTheme.colorScheme.onSurface
            unfocusedTextColor = TextPrimaryColor, // MaterialTheme.colorScheme.onSurface
            cursorColor = PrimaryAppColor, // MaterialTheme.colorScheme.primary
            focusedContainerColor = TextFieldBackgroundColor, // MaterialTheme.colorScheme.surfaceVariant ou similar
            unfocusedContainerColor = TextFieldBackgroundColor,
            disabledContainerColor = TextFieldBackgroundColor,
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorCursorColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorLeadingIconColor = MaterialTheme.colorScheme.error,
            errorTrailingIconColor = MaterialTheme.colorScheme.error
        ),
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = isError,
        supportingText = supportingText
    )
}

@Composable
fun AppBrandingFooter() { // Removido 'private'
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.SelfImprovement,
            contentDescription = "Logo SoftMind", // Deveria ser um stringResource
            tint = PrimaryAppColor, // MaterialTheme.colorScheme.primary
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "SoftMind", // Deveria ser um stringResource
            color = TextSecondaryColor, // MaterialTheme.colorScheme.onSurfaceVariant
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}
