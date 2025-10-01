package br.com.fiap.aura.Screens


import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.model.AvaliacaoRiscosModelResposta


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvaliacaoRiscosPsicossociaisScreen(
    onNavigateToCheckIn: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToAlertas: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    var sentimento by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var relacaoLideranca by remember { mutableStateOf("") }
    var sinaisAlerta by remember { mutableStateOf("") }

    val opcoesCarga = listOf("Leve", "Adequada", "Excessiva")
    val opcoesRelacao = listOf("Boa", "Neutra", "Ruim")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToCheckIn = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToCheckIn()
                },
                onNavigateToVisualizacaoDados = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToVisualizacaoDados()
                },
                onNavigateToCarga = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToCarga()
                },
                onNavigateToAlertas = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToAlertas()
                },
                onNavigateToAvaliacaoRiscos = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToAlertas()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Riscos", color = Color.White) },
                    navigationIcon = {
                        @androidx.compose.runtime.Composable {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
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
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    "Avaliação de Riscos Psicossociais",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50),
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                OutlinedTextField(
                    value = sentimento,
                    onValueChange = { sentimento = it },
                    label = { Text("Como você está se sentindo hoje?", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                )

                SectionTitle("Como você avalia sua carga de trabalho?")
                opcoesCarga.forEach { opcao ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { carga = opcao }
                    ) {
                        RadioButton(
                            selected = carga == opcao,
                            onClick = { carga = opcao },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF4CAF50))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(opcao, color = Color.White, fontSize = 16.sp)
                    }
                }

                Divider(color = Color.DarkGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                SectionTitle("Como você está hoje?")
                opcoesRelacao.forEach { opcao ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { relacaoLideranca = opcao }
                    ) {
                        RadioButton(
                            selected = relacaoLideranca == opcao,
                            onClick = { relacaoLideranca = opcao },
                            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF4CAF50))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(opcao, color = Color.White, fontSize = 16.sp)
                    }
                }

                Divider(color = Color.DarkGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

                OutlinedTextField(
                    value = sinaisAlerta,
                    onValueChange = { sinaisAlerta = it },
                    label = { Text("Você percebe algum sinal de alerta emocional?", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    maxLines = 4,
                    singleLine = false,
                )

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val respostas = listOf(
                                AvaliacaoRiscosModelResposta("Como você está se sentindo hoje?", sentimento),
                                AvaliacaoRiscosModelResposta("Como você avalia sua carga de trabalho?", carga),
                                AvaliacaoRiscosModelResposta("Como você está hoje?", relacaoLideranca),
                                AvaliacaoRiscosModelResposta("Você percebe algum sinal de alerta emocional?", sinaisAlerta)
                            )


                        }
                    },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Enviar", color = Color.Black, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(
        text,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF4CAF50),
        modifier = Modifier.padding(bottom = 8.dp)
    )
}
