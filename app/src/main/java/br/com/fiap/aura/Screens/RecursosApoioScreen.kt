package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecursosApoioScreen(
    onNavigateToCheckIn: () -> Unit = {},
    onNavigateToVisualizacaoDados: () -> Unit = {},
    onNavigateToCarga: () -> Unit = {},
    onNavigateToAvaliacaoRiscos: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recursos de Apoio", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateToCheckIn) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.Black) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.SentimentSatisfied, contentDescription = "Bem-Estar") },
                    label = { Text("Bem-Estar", color = Color.White) },
                    selected = false,
                    onClick = onNavigateToCheckIn
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Visualização") },
                    label = { Text("Visualização", color = Color.White) },
                    selected = false,
                    onClick = onNavigateToVisualizacaoDados
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Lembretes") },
                    label = { Text("Lembretes", color = Color.White) },
                    selected = false,
                    onClick = onNavigateToCarga
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Warning, contentDescription = "Riscos") },
                    label = { Text("Riscos", color = Color.White) },
                    selected = false,
                    onClick = onNavigateToAvaliacaoRiscos
                )
            }
        },
        containerColor = Color.Black
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
            // Canal de escuta
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clickable { /* ação do canal */ },
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
                    Text(
                        "• Pratique pausas durante o expediente.\n• Mantenha uma rotina de sono.\n• Procure atividades prazerosas.",
                        color = Color.White
                    )
                }
            }

            // Alertas e Sinais de Risco
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFB71C1C))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("⚠️ Sinais de Alerta", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(
                        "• Sentimentos persistentes de tristeza.\n• Irritabilidade constante.\n• Isolamento social ou fadiga extrema.",
                        color = Color.White
                    )
                }
            }
        }
    }
}
