package br.com.fiap.aura.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioGroupQuestion(
    question: String,
    selected: Int,
    onSelectedChange: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = question,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            (1..5).forEach { value ->
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    RadioButton(
                        selected = selected == value,
                        onClick = { onSelectedChange(value) }
                    )
                    Text(text = value.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
