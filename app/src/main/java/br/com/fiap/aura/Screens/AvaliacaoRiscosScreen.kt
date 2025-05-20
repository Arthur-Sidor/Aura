package br.com.fiap.aura.Screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvaliacaoRiscosPsicossociaisScreen(
    onNavigateToBemEstar: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToLembretes: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit
) {
    var sentimento by remember { mutableStateOf("") }
    var carga by remember { mutableStateOf("") }
    var relacaoLideranca by remember { mutableStateOf("") }
    var sinaisAlerta by remember { mutableStateOf("") }

    val opcoesCarga = listOf("Leve", "Adequada", "Excessiva")
    val opcoesRelacao = listOf("Boa", "Neutra", "Ruim")

    Column(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            "Avaliação de Riscos Psicossociais",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = sentimento,
            onValueChange = { sentimento = it },
            label = { Text("Como você está se sentindo hoje?") },
            modifier = Modifier.fillMaxWidth()
        )

        Text("Como você avalia sua carga de trabalho?")
        opcoesCarga.forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = carga == it, onClick = { carga = it })
                Text(it)
            }
        }

        Text("Como está sua relação com a liderança?")
        opcoesRelacao.forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = relacaoLideranca == it, onClick = { relacaoLideranca = it })
                Text(it)
            }
        }

        OutlinedTextField(
            value = sinaisAlerta,
            onValueChange = { sinaisAlerta = it },
            label = { Text("Você percebe algum sinal de alerta emocional?") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                Log.d(
                    "AVALIACAO",
                    "Sentimento: $sentimento, Carga: $carga, Liderança: $relacaoLideranca, Alertas: $sinaisAlerta"
                )
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar")
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Botões para navegar entre telas
        Button(onClick = onNavigateToBemEstar, modifier = Modifier.fillMaxWidth()) {
            Text("Ir para Bem-Estar")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToVisualizacaoDados, modifier = Modifier.fillMaxWidth()) {
            Text("Ir para Visualização de Dados")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToLembretes, modifier = Modifier.fillMaxWidth()) {
            Text("Ir para Lembretes")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = onNavigateToRecursosApoio, modifier = Modifier.fillMaxWidth()) {
            Text("Ir para Recursos de Apoio")
        }
    }
}