package br.com.fiap.aura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.Components.PerguntaRadio
import br.com.fiap.aura.viewmodel.LiderancaViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiderancaScreen(
    viewModel: LiderancaViewModel = LiderancaViewModel(),
    onNavigateToCheckIn: () -> Unit,
    onNavigateToComunicacao: () -> Unit,
    onNavigateToAlertas: () -> Unit,
    onNavigateToRelacionamentos: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToLideranca: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Coleta dos estados, incluindo a nova média geral
    val interesse by viewModel.interesseBemEstar.collectAsState()
    val disponibilidade by viewModel.disponibilidadeOuvir.collectAsState()
    val conforto by viewModel.confortoReportar.collectAsState()
    val reconhecimento by viewModel.reconhecimento.collectAsState()
    val historico by viewModel.historico.collectAsState()
    val mediaGeral by viewModel.mediaGeral.collectAsState() // <-- Coletar a nova média

    LaunchedEffect(Unit) {
        viewModel.carregarHistorico()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToLideranca = { coroutineScope.launch { drawerState.close() } },
                onNavigateToComunicacao = { coroutineScope.launch { drawerState.close() }; onNavigateToComunicacao() },
                onNavigateToCheckIn = { coroutineScope.launch { drawerState.close() }; onNavigateToCheckIn() },
                onNavigateToAlertas = { coroutineScope.launch { drawerState.close() }; onNavigateToAlertas() },
                onNavigateToRelacionamentos = { coroutineScope.launch { drawerState.close() }; onNavigateToRelacionamentos() },
                onNavigateToCarga = { coroutineScope.launch { drawerState.close() }; onNavigateToCarga() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Avaliação de Liderança", color = Color.White) },
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
                Text(
                    "Avalie de 1 a 5 cada aspecto da sua liderança",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Pergunta 1... (código das perguntas permanece o mesmo)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Minha liderança demonstra interesse pelo meu bem-estar no trabalho",
                            opcoes = listOf("1", "2", "3", "4", "5"),
                            selecionado = interesse.toString(),
                            onSelecionar = { viewModel.atualizarInteresse(it.toInt()) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 2
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Minha liderança está disponível para me ouvir quando necessário",
                            opcoes = listOf("1", "2", "3", "4", "5"),
                            selecionado = disponibilidade.toString(),
                            onSelecionar = { viewModel.atualizarDisponibilidade(it.toInt()) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 3
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Me sinto confortável para reportar problemas ou dificuldades ao meu líder",
                            opcoes = listOf("1", "2", "3", "4", "5"),
                            selecionado = conforto.toString(),
                            onSelecionar = { viewModel.atualizarConforto(it.toInt()) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Pergunta 4
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Minha liderança reconhece minhas entregas e esforços",
                            opcoes = listOf("1", "2", "3", "4", "5"),
                            selecionado = reconhecimento.toString(),
                            onSelecionar = { viewModel.atualizarReconhecimento(it.toInt()) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Pergunta 5
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PerguntaRadio(
                            titulo = "Existe confiança e transparência na relação com minha liderança",
                            opcoes = listOf("1", "2", "3", "4", "5"),
                            selecionado = reconhecimento.toString(),
                            onSelecionar = { viewModel.atualizarReconhecimento(it.toInt()) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { viewModel.registrarAvaliacao() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56A8C2)),
                    // Botão fica desabilitado se nenhuma resposta foi alterada do valor inicial (3)
                    enabled = listOf(interesse, disponibilidade, conforto, reconhecimento).any { it != 0 }
                ) {
                    Text("Registrar Avaliação", color = Color.White)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- NOVA SEÇÃO: HISTÓRICO E MÉDIA ---
                Text("Histórico e Média Geral", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))

                // Card para a Média Geral
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("MÉDIA GERAL", color = Color.Gray, fontSize = 12.sp)
                        Text(
                            text = String.format(Locale.getDefault(), "%.1f", mediaGeral),
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF56A8C2)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de Histórico com Média Individual por Card
                historico.forEach { item ->
                    val mediaItem = listOf(item.interesseBemEstar, item.disponibilidadeOuvir, item.confortoReportar, item.reconhecimento).average()
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(item.data, color = Color.White, fontWeight = FontWeight.Bold)
                            Text(
                                "Média: ${String.format("%.1f", mediaItem)}",
                                color = Color(0xFF56A8C2)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
