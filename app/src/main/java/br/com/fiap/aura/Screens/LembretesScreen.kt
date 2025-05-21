package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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

@OptIn(ExperimentalMaterial3Api::class)
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
                icone = { Icon(Icons.Filled.Notifications, contentDescription = "Lembrete", tint = Color.White) }
            ),
            Lembrete(
                titulo = "Pratique respiração",
                descricao = "Faça uma pausa para exercícios de respiração profunda por 5 minutos.",
                icone = { Icon(Icons.Filled.Info, contentDescription = "Dica", tint = Color.White) }
            ),
            Lembrete(
                titulo = "Converse com alguém",
                descricao = "Se sentir sobrecarregado, procure ajuda de amigos ou um profissional.",
                icone = { Icon(Icons.Filled.Star, contentDescription = "Apoio", tint = Color.White) }
            ),
        )
    }

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = { Text("Lembretes & Dicas", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateToBemEstar) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.DarkGray) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.SelfImprovement, contentDescription = "Bem-Estar") },
                    label = { Text("Bem-Estar") },
                    selected = false,
                    onClick = onNavigateToBemEstar
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Dados") },
                    label = { Text("Dados") },
                    selected = false,
                    onClick = onNavigateToVisualizacaoDados
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Warning, contentDescription = "Riscos") },
                    label = { Text("Riscos") },
                    selected = false,
                    onClick = onNavigateToAvaliacaoRiscos
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.SupportAgent, contentDescription = "Apoio") },
                    label = { Text("Apoio") },
                    selected = false,
                    onClick = onNavigateToRecursosApoio
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            lembretes.forEach { lembrete ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
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
        }
    }
}
