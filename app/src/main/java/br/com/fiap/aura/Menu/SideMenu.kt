
package br.com.fiap.aura.Menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SideMenu(
    onNavigateToCheckIn: () -> Unit,
    onNavigateToVisualizacaoDados: () -> Unit,
    onNavigateToCarga: () -> Unit,
    onNavigateToRecursosApoio: () -> Unit,
    onNavigateToAvaliacaoRiscos: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(200.dp)
            .background(Color(0xFF121212))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MenuItem("Checkin", onNavigateToCheckIn)
        MenuItem("Visualização de Dados", onNavigateToVisualizacaoDados)
        MenuItem("Carga De Trabalho", onNavigateToCarga)
        MenuItem("Recursos de Apoio", onNavigateToRecursosApoio)
        MenuItem("Avaliação de Riscos", onNavigateToAvaliacaoRiscos)
    }
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 8.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp)
    }
}
