package br.com.fiap.aura.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoricoComunicacaoCard(
    data: String,
    interesse: Int,
    disponibilidade: Int,
    conforto: Int,
    reconhecimento: Int,
    totalAvaliacoes: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Histórico de Comunicação com a Liderança",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Avaliação Anterior: $data", color = Color.Black)
            Text("Interesse pelo bem-estar: $interesse", color = Color.Black)
            Text("Disponibilidade para ouvir: $disponibilidade", color = Color.Black)
            Text("Conforto ao reportar: $conforto", color = Color.Black)
            Text("Reconhecimento: $reconhecimento", color = Color.Black)

            Spacer(modifier = Modifier.height(8.dp))

            Text("Total de Avaliações: $totalAvaliacoes", fontWeight = FontWeight.Medium, color = Color.Black)
        }
    }
}
