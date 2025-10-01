package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aura.Components.HistoricoCard
import br.com.fiap.aura.Components.PerguntaRadio
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.Viewmodel.CargaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CargaScreen(
    viewModel: CargaViewModel = CargaViewModel(),
    onNavigateToCheckIn: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToAlertas: () -> Unit,
    onNavigateToRelacionamentos: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val cargaSelecionada by viewModel.cargaSelecionada.collectAsState()
    val impactoSelecionado by viewModel.impactoSelecionado.collectAsState()
    val horasExtrasSelecionada by viewModel.horasExtrasSelecionada.collectAsState()
    val historico by viewModel.historico.collectAsState()

    // Carrega histórico ao abrir a tela
    LaunchedEffect(Unit) {
        viewModel.carregarHistorico()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToCarga = { coroutineScope.launch { drawerState.close() } },
                onNavigateToVisualizacaoDados = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToVisualizacaoDados()
                },
                onNavigateToCheckIn = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToCheckIn()
                },
                onNavigateToAlertas = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToAlertas()
                },
                onNavigateToRelacionamentos = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToRelacionamentos()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Carga de Trabalho", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menu", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
                )
            },
            containerColor = Color.Black
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Avalie sua carga de trabalho atual", fontSize = 18.sp, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 1
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Como você avalia sua carga de trabalho?",
                            opcoes = listOf("Muito Leve", "Leve", "Média", "Alta", "Muito Alta"),
                            selecionado = cargaSelecionada,
                            onSelecionar = { viewModel.atualizarCarga(it) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 2
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Sua carga de trabalho afeta sua qualidade de vida?",
                            opcoes = listOf("Não", "Raramente", "Às vezes", "Frequentemente", "Sempre"),
                            selecionado = impactoSelecionado,
                            onSelecionar = { viewModel.atualizarImpacto(it) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 3
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Você trabalha além do seu horário regular?",
                            opcoes = listOf("Não", "Raramente", "Às vezes", "Frequentemente", "Sempre"),
                            selecionado = horasExtrasSelecionada,
                            onSelecionar = { viewModel.atualizarHorasExtras(it) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.registrarAvaliacao() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56A8C2))
                ) {
                    Text("Registrar Avaliação", color = Color.White)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Histórico de respostas
                Text("Histórico de Avaliações", fontSize = 18.sp, color = Color.White)

                Spacer(modifier = Modifier.height(8.dp))

                historico.forEach { item ->
                    HistoricoCard(
                        ultimaData = item.data,
                        ultimaCarga = item.carga,
                        ultimoImpacto = item.impacto,
                        ultimasHorasExtras = item.horasExtras,
                        totalAvaliacoes = viewModel.totalAvaliacoes(),
                        mediaCarga = viewModel.mediaCarga()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
