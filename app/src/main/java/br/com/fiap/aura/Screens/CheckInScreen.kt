
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import br.com.fiap.aura.Menu.SideMenu
import br.com.fiap.aura.viewModel.CheckInViewModel


import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInScreen(
    viewModel: CheckInViewModel = CheckInViewModel(),
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToAlertas: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Observa mensagens da ViewModel e exibe Snackbar
    LaunchedEffect(viewModel.snackbarMessage.value) {
        viewModel.snackbarMessage.value?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.snackbarMessage.value = null
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SideMenu(
                onNavigateToCheckIn = { coroutineScope.launch { drawerState.close() } },
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
                    onNavigateToAvaliacaoRiscos()
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CheckIn", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menu", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
                )
            },
            containerColor = Color.Black,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->

            val opcoesHumor = listOf("😀 Feliz", "😐 Neutro", "😔 Triste", "😠 Irritado", "😟 Ansioso")
            var isFocused by remember { mutableStateOf(false) }

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
                    text = "CheckIn Emocional",
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
                                if (viewModel.humorSelecionado.value == humor) Color(0xFF4CAF50) else Color.DarkGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { viewModel.humorSelecionado.value = humor }
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
                        value = viewModel.descricao.value,
                        onValueChange = { viewModel.descricao.value = it },
                        modifier = Modifier
                            .fillMaxSize()
                            .onFocusChanged { isFocused = it.isFocused },
                        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                        cursorBrush = SolidColor(Color(0xFF4CAF50))
                    )
                    if (viewModel.descricao.value.isEmpty() && !isFocused) {
                        Text(
                            text = "Descreva seu dia ou sentimentos",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { viewModel.enviarCheckIn() },
                    enabled = viewModel.humorSelecionado.value != null && viewModel.descricao.value.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Check-in", color = Color.White)
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Histórico
                Text(
                    text = "Histórico de Check-ins",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                viewModel.checkInHistory.forEach { checkIn ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(text = checkIn.humor, color = Color.White, fontWeight = FontWeight.Bold)
                            Text(text = checkIn.descricao, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Resumo
                val humorCounts: Map<String, Int> = viewModel.checkInHistory.groupingBy { it.humor }.eachCount()
                Text(
                    text = "Resumo dos humores:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                humorCounts.forEach { (humor, count) ->
                    Text(text = "$humor: $count vezes", color = Color.White)
                }
            }
        }
    }
}