package br.com.fiap.aura.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class PerguntaRadio {
}
@Composable
fun PerguntaRadio(
    titulo: String,
    descricao: String? = null,
    opcoes: List<String>,
    selecionado: String,
    onSelecionar: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Título em branco
        Text(titulo, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White)

        // Descrição (opcional) com azul
        if (descricao != null) {
            Text(descricao, fontSize = 14.sp, color = Color(0xFF2196F3))
        }

        Spacer(modifier = Modifier.height(8.dp))

        opcoes.forEach { opcao ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = selecionado == opcao,
                    onClick = { onSelecionar(opcao) }
                )
                Spacer(modifier = Modifier.width(8.dp))

                // Opções em branco
                Text(opcao, color = Color.White)
            }
        }
    }
}
