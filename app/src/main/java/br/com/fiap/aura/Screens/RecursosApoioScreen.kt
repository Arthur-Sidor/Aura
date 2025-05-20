package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecursosApoioScreen(
    onNavigateToBemEstar: () -> Unit = {},
    onNavigateToVisualizacaoDados: () -> Unit = {},
    onNavigateToLembretes: () -> Unit = {},
    onNavigateToAvaliacaoRiscos: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Recursos de Apoio",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Canal de escuta
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable {
                    // Aqui você pode abrir um chat, enviar e-mail, etc.
                },
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("📞 Canal de Escuta", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("Converse com um profissional de forma anônima e segura.", color = Color.White)
            }
        }

        // Orientações de Bem-Estar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1B5E20))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💡 Orientações", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("• Pratique pausas durante o expediente.\n• Mantenha uma rotina de sono.\n• Procure atividades prazerosas.", color = Color.White)
            }
        }

        // Alertas e Sinais de Risco
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFB71C1C))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("⚠️ Sinais de Alerta", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Text("• Sentimentos persistentes de tristeza.\n• Irritabilidade constante.\n• Isolamento social ou fadiga extrema.", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botões para navegação entre telas
        Button(
            onClick = onNavigateToBemEstar,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Ir para Bem-Estar")
        }

        Button(
            onClick = onNavigateToVisualizacaoDados,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Ir para Visualização de Dados")
        }

        Button(
            onClick = onNavigateToLembretes,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Ir para Lembretes")
        }

        Button(
            onClick = onNavigateToAvaliacaoRiscos,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text("Ir para Avaliação de Riscos Psicossociais")
        }
    }
}
