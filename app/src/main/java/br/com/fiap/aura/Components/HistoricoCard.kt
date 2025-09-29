package br.com.fiap.aura.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

class HistoricoCard {
}

@Composable
fun HistoricoCard(
    ultimaData: String,
    ultimaCarga: String,
    ultimoImpacto: String,
    ultimasHorasExtras: String,
    totalAvaliacoes: Int,
    mediaCarga: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFEFEF))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Histórico de Avaliações", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Avaliação Anterior: $ultimaData")
            Text("Carga: $ultimaCarga")
            Text("Impacto na vida: $ultimoImpacto")
            Text("Horas extras: $ultimasHorasExtras")

            Spacer(modifier = Modifier.height(8.dp))

            Text("Total de Avaliações: $totalAvaliacoes")
            Text("Média da carga: $mediaCarga", fontWeight = FontWeight.Medium)
        }
    }
}
