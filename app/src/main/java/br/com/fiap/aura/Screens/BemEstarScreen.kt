package br.com.fiap.aura.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.aura.ApiMock.RetrofitClient
import kotlinx.coroutines.launch
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.model.BemEstarRequestModel
import br.com.fiap.aura.ApiMock.BemEstarApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BemEstarScreen(
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToLembretes: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToBemEstar = {
                    coroutineScope.launch { drawerState.close() }
                },
                onNavigateToVisualizacaoDados = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToVisualizacaoDados()
                },
                onNavigateToLembretes = {
                    coroutineScope.launch { drawerState.close() }
                    onNavigateToLembretes()
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Bem-estar", color = Color.White) },
                    navigationIcon = {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color.Black) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.SentimentSatisfied, contentDescription = "Bem-Estar") },
                        label = { Text("RecursosApoio", color = Color.White) },
                        selected = false,
                        onClick = onNavigateToRecursosApoio
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
                        selected = false,
                        onClick = onNavigateToLembretes
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Warning, contentDescription = "Riscos") },
                        label = { Text("Riscos", color = Color.White) },
                        selected = false,
                        onClick = onNavigateToAvaliacaoRiscos
                    )
                }
            },
            containerColor = Color.Black,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->

            var humorSelecionado by remember { mutableStateOf<String?>(null) }
            var descricao by remember { mutableStateOf("") }
            var isFocused by remember { mutableStateOf(false) }
            val opcoesHumor = listOf("😀 Feliz", "😐 Neutro", "😔 Triste", "😠 Irritado", "😟 Ansioso")

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
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
                        Text(text = humor, color = Color.White, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF101010))
                        .border(
                            width = 2.dp,
                            color = if (isFocused) Color(0xFF4CAF50) else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = descricao,
                        onValueChange = { descricao = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .onFocusChanged { isFocused = it.isFocused },
                        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                        cursorBrush = SolidColor(Color(0xFF4CAF50))
                    )
                    if (descricao.isEmpty() && !isFocused) {
                        Text(
                            text = "Descreva seu dia ou sentimentos",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            try {
                                val response = RetrofitClient.bemEstarApi.enviarCheckin(
                                    BemEstarRequestModel(
                                        humor = humorSelecionado ?: "",
                                        descricao = descricao
                                    )
                                )
                                if (response.isSuccessful) {
                                    snackbarHostState.showSnackbar("Check-in salvo com sucesso!")
                                } else {
                                    snackbarHostState.showSnackbar("Erro ao salvar check-in.")
                                }
                            } catch (e: Exception) {
                                snackbarHostState.showSnackbar("Falha na conexão: ${e.message}")
                            }
                        }
                    },
                    enabled = humorSelecionado != null && descricao.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Check-in", color = Color.White)
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}
