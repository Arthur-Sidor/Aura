package br.com.fiap.aura.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.Viewmodel.RelacionamentosViewModel
import br.com.fiap.aura.model.RelacionamentosModel
import br.com.fiap.aura.model.ResponseRelacionamentosModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelacionamentoScreen(
    viewModel: RelacionamentosViewModel = RelacionamentosViewModel(),
    onNavigateToCheckIn: () -> Unit,
    onNavigateToComunicacao: () -> Unit,
    onNavigateToAlertas: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToLideranca: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val relacionamentos = viewModel.relacionamentos
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage
    var showSuccess by remember { mutableStateOf(false) }

    // Estado local para armazenar respostas selecionadas por pergunta
    val respostasSelecionadas = remember { mutableStateMapOf<String, String>() }

    LaunchedEffect(Unit) { viewModel.carregarRelacionamentos() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToRelacionamentos = { coroutineScope.launch { drawerState.close() } },
                onNavigateToComunicacao = { coroutineScope.launch { drawerState.close() }; onNavigateToComunicacao() },
                onNavigateToCarga = { coroutineScope.launch { drawerState.close() }; onNavigateToCarga() },
                onNavigateToAlertas = { coroutineScope.launch { drawerState.close() }; onNavigateToAlertas() },
                onNavigateToCheckIn = { coroutineScope.launch { drawerState.close() }; onNavigateToCheckIn() },
                onNavigateToLideranca = { coroutineScope.launch { drawerState.close() }; onNavigateToLideranca() }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Relacionamentos", color = Color.White) },
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

                // Loading
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(vertical = 16.dp),
                        color = Color(0xFF56A8C2)
                    )
                }

                // Lista de relacionamentos
                relacionamentos.forEach { relacionamento ->
                    RelacionamentoCard(
                        relacionamento = relacionamento,
                        selectedAnswer = respostasSelecionadas[relacionamento.id] ?: "",
                        onRespostaSelecionada = { resposta ->
                            respostasSelecionadas[relacionamento.id ?: ""] = resposta
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Média em tempo real
                val mediaAtual = respostasSelecionadas.values.mapNotNull { it.toIntOrNull() }.let {
                    if (it.isEmpty()) 0.0 else it.sum().toDouble() / it.size
                }
                Text(
                    text = "Média atual: ${"%.2f".format(mediaAtual)}",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                // Botão Enviar
                Button(
                    onClick = {
                        respostasSelecionadas.forEach { (perguntaId, resposta) ->
                            viewModel.salvarResposta(
                                ResponseRelacionamentosModel(
                                    perguntaId = perguntaId,
                                    resposta = resposta,
                                    idModelReport = "relacionamentos2025",
                                    idsResponse = emptyList()
                                )
                            )
                        }

                        viewModel.enviarRespostas(
                            onSuccess = { media -> showSuccess = true },
                            onError = { showSuccess = false }
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Enviar")
                }

                // Mensagem de sucesso
                if (showSuccess) {
                    Text(
                        text = "Respostas enviadas com sucesso!",
                        color = Color(0xFF56A8C2),
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                // Mensagem de erro
                if (errorMessage != null) {
                    Text(
                        text = "Erro: $errorMessage",
                        color = Color.Red,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun RelacionamentoCard(
    relacionamento: RelacionamentosModel,
    selectedAnswer: String,
    onRespostaSelecionada: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = relacionamento.texto,
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            relacionamento.respostas.forEach { (id, texto) ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRespostaSelecionada(id) }
                        .padding(vertical = 6.dp)
                ) {
                    RadioButton(
                        selected = selectedAnswer == id,
                        onClick = { onRespostaSelecionada(id) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF56A8C2),
                            unselectedColor = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = texto, color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}
