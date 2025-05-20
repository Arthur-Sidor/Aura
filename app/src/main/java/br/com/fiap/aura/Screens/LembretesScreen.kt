package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Lembrete(
    val titulo: String,
    val descricao: String,
    val icone: @Composable () -> Unit,
    val acao: () -> Unit = {}
)

@Composable
fun LembretesScreen(
    onNavigateToBemEstar: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit
) {
    val lembretes = remember {
        listOf(
            Lembrete(
                titulo = "Beba água",
                descricao = "Mantenha-se hidratado para melhorar o foco e o humor.",
                icone = { Icon(Icons.Filled.Notifications, contentDescription = "Lembrete") },
                acao = { /* Pode abrir detalhes ou outra tela */ }
            ),
            Lembrete(
                titulo = "Pratique respiração",
                descricao = "Faça uma pausa para exercícios de respiração profunda por 5 minutos.",
                icone = { Icon(Icons.Filled.Info, contentDescription = "Dica") }
            ),
            Lembrete(
                titulo = "Converse com alguém",
                descricao = "Se sentir sobrecarregado, procure ajuda de amigos ou um profissional.",
                icone = { Icon(Icons.Filled.Star, contentDescription = "Apoio") }
            ),
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Lembretes & Dicas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        lembretes.forEach { lembrete ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { lembrete.acao() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFF4CAF50), shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        lembrete.icone()
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = lembrete.titulo,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = lembrete.descricao,
                            color = Color.LightGray,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Botões de navegação
        Button(
            onClick = onNavigateToBemEstar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir para Bem-Estar")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onNavigateToVisualizacaoDados,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir para Visualização de Dados")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onNavigateToAvaliacaoRiscos,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir para Avaliação de Riscos Psicossociais")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = onNavigateToRecursosApoio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ir para Recursos de Apoio")
        }
    }
}
