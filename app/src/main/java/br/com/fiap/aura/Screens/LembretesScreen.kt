package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import br.com.fiap.aura.Menu.SideMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LembretesScreen(
    onNavigateToBemEstar: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val lembretes = listOf(
        Pair("🧊", "Beba água — Mantenha-se hidratado para melhorar o foco e o humor."),
        Pair("💨", "Pratique respiração — Faça uma pausa para exercícios de respiração profunda."),
        Pair("💬", "Converse com alguém — Procure ajuda se estiver sobrecarregado.")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToBemEstar = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToBemEstar()
                },
                onNavigateToVisualizacaoDados = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToVisualizacaoDados()
                },
                onNavigateToLembretes = {
                    coroutineScope.launch { drawerState.close() }
                },
                onNavigateToRecursosApoio = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToRecursosApoio()
                },
                onNavigateToAvaliacaoRiscos = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToAvaliacaoRiscos()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101010))  // Fundo escuro geral
        ) {
            Scaffold(
                containerColor = Color.Transparent, // deixa o fundo do scaffold transparente
                topBar = {
                    TopAppBar(
                        title = { Text("Recursos de Apoio", color = Color.White) },
                        navigationIcon = {
                            IconButton(onClick = onNavigateToBemEstar) {
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
                            onClick = onNavigateToBemEstar
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
                            selected = true,
                            onClick = onNavigateToRecursosApoio
                        )
                        NavigationBarItem(
                            icon = { Icon(Icons.Default.Warning, contentDescription = "Riscos") },
                            label = { Text("Riscos", color = Color.White) },
                            selected = false,
                            onClick = onNavigateToAvaliacaoRiscos
                        )
                    }
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    lembretes.forEach { (emoji, texto) ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF202020), shape = RoundedCornerShape(12.dp)) // Caixa mais clara que o fundo
                                .padding(16.dp)
                        ) {
                            Text(
                                text = emoji,
                                fontSize = 32.sp,
                                modifier = Modifier.padding(end = 16.dp),
                                color = Color.White
                            )
                            Text(
                                text = texto,
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}
