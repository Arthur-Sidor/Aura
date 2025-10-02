package br.com.fiap.aura.screens

import ComunicacaoViewModel
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
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComunicacaoScreen(
    // A injeção do ViewModel e os callbacks de navegação permanecem os mesmos
    viewModel: ComunicacaoViewModel = ComunicacaoViewModel(),
    onNavigateToCheckIn: () -> Unit,
    onNavigateToAlertas: () -> Unit,
    onNavigateToRelacionamentos: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToLideranca: () -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    // Coletando os novos estados da ViewModel
    val perguntas by viewModel.perguntas.collectAsState()
    val historico by viewModel.historico.collectAsState()
    val mediaGeral by viewModel.mediaGeral.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToComunicacao = { coroutineScope.launch { drawerState.close() } },
                onNavigateToCheckIn = { coroutineScope.launch { drawerState.close() }; onNavigateToCheckIn() },
                onNavigateToAlertas = { coroutineScope.launch { drawerState.close() }; onNavigateToAlertas() },
                onNavigateToRelacionamentos = { coroutineScope.launch { drawerState.close() }; onNavigateToRelacionamentos() },
                onNavigateToCarga = { coroutineScope.launch { drawerState.close() }; onNavigateToCarga() },
                onNavigateToLideranca = { coroutineScope.launch { drawerState.close() }; onNavigateToLideranca() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Comunicação Corporativa", color = Color.White) },
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
                    "Como você avalia a comunicação na empresa?",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                // As perguntas agora são consumidas do StateFlow 'perguntas'
                perguntas.forEachIndexed { index, pergunta ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            PerguntaRadio(
                                titulo = pergunta.pergunta,
                                opcoes = listOf("1", "2", "3", "4", "5"),
                                selecionado = if (pergunta.resposta == 0) "" else pergunta.resposta.toString(),
                                onSelecionar = { resposta -> viewModel.atualizarResposta(index, resposta.toInt()) }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    // Chama a função registrarAvaliacao da ViewModel
                    onClick = { viewModel.registrarAvaliacao() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56A8C2)),
                    // O botão é desabilitado se nenhuma resposta foi dada
                    enabled = perguntas.any { it.resposta > 0 }
                ) {
                    Text("Registrar Avaliação", color = Color.White)
                }

                Spacer(modifier = Modifier.height(32.dp))

                // --- NOVA SEÇÃO: HISTÓRICO E MÉDIA ---
                Text(
                    "Histórico e Média",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
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

                // Lista de Cards do Histórico
                historico.forEach { avaliacao ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D2D))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(avaliacao.data, color = Color.White, fontWeight = FontWeight.Bold)
                            Text("Média: ${String.format("%.1f", avaliacao.media)}", color = Color(0xFF56A8C2))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
