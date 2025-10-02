package br.com.fiap.aura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.aura.screens.ComunicacaoScreen
import br.com.fiap.aura.ui.theme.AuraTheme

class VisualizaçãoDadosTESTE : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraTheme {
                ComunicacaoScreen()
            }
        }
    }
}
