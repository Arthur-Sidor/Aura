package br.com.fiap.aura.Screens

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
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.aura.Components.PerguntaRadio
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.viewmodel.AlertasViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertasScreen(
    viewModel: AlertasViewModel = viewModel(),
    onNavigateToCheckIn: () -> Unit = {},
    onNavigateToVisualizacaoDados: () -> Unit = {},
    onNavigateToCarga: () -> Unit = {},
    onNavigateToAvaliacaoRiscos: () -> Unit = {}
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val respostasSelecionadas by viewModel.respostasSelecionadas
    val historico by viewModel.historico

    val perguntas = listOf(
        "Você tem apresentado sintomas como insônia, irritabilidade ou cansaço extremo?",
        "Você sente que sua saúde mental prejudica sua produtividade no trabalho?"
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToAlertas = { coroutineScope.launch { drawerState.close() } },
                onNavigateToVisualizacaoDados = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToVisualizacaoDados()
                },
                onNavigateToCheckIn = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToCheckIn()
                },
                onNavigateToCarga = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToCarga()
                },
                onNavigateToAvaliacaoRiscos = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToAvaliacaoRiscos()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Alertas", color = Color.White) },
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
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text("Avaliação Atual", fontSize = 18.sp, color = Color.White)

                perguntas.forEach { pergunta ->
                    val resposta = respostasSelecionadas.find { it.pergunta == pergunta }?.resposta ?: ""
                    PerguntaRadio(
                        titulo = pergunta,
                        opcoes = listOf("Nunca", "Raramente", "Às vezes", "Frequentemente", "Sempre"),
                        selecionado = resposta,
                        onSelecionar = { viewModel.atualizarResposta(pergunta, it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Button(
                    onClick = { viewModel.finalizarAvaliação() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
                ) {
                    Text("Finalizar", color = Color.White)
                }

                Divider(color = Color.Gray, thickness = 1.dp)

                // Histórico
                Text("Histórico", fontSize = 18.sp, color = Color.White)
                if (historico.isEmpty()) {
                    Text("Nenhuma avaliação registrada", color = Color.Gray)
                } else {
                    historico.forEachIndexed { index, aval ->
                        val media = viewModel.calcularMedia(aval)
                        val nivel = viewModel.nivelRisco(media)

                        Text("Avaliação ${index + 1}:", color = Color.White)
                        aval.forEach { pr ->
                            Text("${pr.pergunta} → ${pr.resposta}", color = Color.Gray)
                        }
                        Text("Média: %.1f | Nível de Risco: $nivel".format(media), color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
