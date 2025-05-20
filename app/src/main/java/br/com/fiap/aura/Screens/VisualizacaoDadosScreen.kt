package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VisualizacaoDadosScreen(
    onNavigateToBemEstar: () -> Unit = {},
    onNavigateToLembretes: () -> Unit = {},
    onNavigateToRecursosApoio: () -> Unit = {},
    onNavigateToAvaliacaoRiscos: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Black)
    ) {
        Text(
            text = "Resumo do Bem-Estar",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        IndicadorItem(titulo = "Humor médio da semana", valor = 0.75f, texto = "Bom")
        Spacer(modifier = Modifier.height(16.dp))
        IndicadorItem(titulo = "Nível de estresse", valor = 0.35f, texto = "Baixo")
        Spacer(modifier = Modifier.height(16.dp))
        IndicadorItem(titulo = "Qualidade do sono", valor = 0.65f, texto = "Regular")
        Spacer(modifier = Modifier.height(16.dp))
        IndicadorItem(titulo = "Motivação geral", valor = 0.80f, texto = "Alta")

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Continue preenchendo seu diário de humor e check-ins para acompanhar seu progresso.",
            fontSize = 16.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Exemplo simples de botões para navegar (opcional)
        Button(onClick = onNavigateToBemEstar, modifier = Modifier.fillMaxWidth()) {
            Text("Voltar ao Bem-Estar")
        }
    }
}

@Composable
fun IndicadorItem(titulo: String, valor: Float, texto: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = titulo, color = Color.White, fontSize = 16.sp)
        LinearProgressIndicator(
            progress = valor,
            color = Color(0xFF4CAF50),
            trackColor = Color.DarkGray,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(50))
                .padding(vertical = 6.dp)
        )
        Text(text = texto, color = Color(0xFFB2FF59), fontSize = 14.sp)
    }
}
