package br.com.fiap.aura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.fiap.aura.Screens.LembretesScreen
import br.com.fiap.aura.Screens.RecursosApoioScreen
import br.com.fiap.aura.Screens.VisualizacaoDadosScreen
import br.com.fiap.aura.ui.theme.AuraTheme

class LembretesTESTE : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraTheme {
                LembretesScreen()
            }
        }
    }
}
