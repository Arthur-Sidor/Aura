package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BemEstarScreen(
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToLembretes: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit
) {
    var humorSelecionado by remember { mutableStateOf<String?>(null) }
    var descricao by remember { mutableStateOf("") }

    val opcoesHumor = listOf("😀 Feliz", "😐 Neutro", "😔 Triste", "😠 Irritado", "😟 Ansioso")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Acompanhamento do Bem-Estar Emocional",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Como você está se sentindo hoje?",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Botões de humor
        opcoesHumor.forEach { humor ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(
                        if (humorSelecionado == humor) Color(0xFF4CAF50) else Color.DarkGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { humorSelecionado = humor }
                    .padding(12.dp)
            ) {
                Text(
                    text = humor,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descreva seu dia ou sentimentos") },
            placeholder = { Text("Ex: Me senti sobrecarregado no trabalho...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF4CAF50),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                focusedContainerColor = Color.DarkGray,
                unfocusedContainerColor = Color.DarkGray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Aqui você pode salvar no banco ou exibir uma mensagem
            },
            enabled = humorSelecionado != null && descricao.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Check-in", color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Botões para navegar para outras telas
        Button(
            onClick = onNavigateToVisualizacaoDados,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Visualização de Dados Consolidados")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onNavigateToLembretes,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lembretes, Dicas e Apoio")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onNavigateToRecursosApoio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recursos de Apoio")
        }
    }
}
