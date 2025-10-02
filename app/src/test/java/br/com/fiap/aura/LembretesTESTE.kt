package br.com.fiap.aura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.fiap.aura.screens.LembretesScreen
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
