package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualizacaoDadosScreen(
    onNavigateToCheckIn: () -> Unit = {},
    onNavigateToCarga: () -> Unit = {},
    onNavigateToAlertas: () -> Unit = {},
    onNavigateToAvaliacaoRiscos: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Visualização de Dados", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateToCheckIn) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors =  TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
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
                    selected = true,
                    onClick = { } // já está na tela
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
                .padding(20.dp)
        ) {
            Text(
                text = "Resumo do Bem-Estar",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            IndicadorItem(titulo = "Humor médio da semana", valor = 0.75f, texto = "Bom 😊")
            Spacer(modifier = Modifier.height(16.dp))
            IndicadorItem(titulo = "Nível de estresse", valor = 0.35f, texto = "Baixo 😌")
            Spacer(modifier = Modifier.height(16.dp))
            IndicadorItem(titulo = "Qualidade do sono", valor = 0.65f, texto = "Regular 😴")
            Spacer(modifier = Modifier.height(16.dp))
            IndicadorItem(titulo = "Motivação geral", valor = 0.80f, texto = "Alta 💪")

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Continue preenchendo seu diário de humor e check-ins para acompanhar seu progresso.",
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun IndicadorItem(titulo: String, valor: Float, texto: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = titulo, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        LinearProgressIndicator(
            progress = valor,
            color = Color(0xFF4CAF50),
            trackColor = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(50))
                .padding(vertical = 6.dp)
        )
        Text(text = texto, color = Color(0xFFB2FF59), fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
    }
}
