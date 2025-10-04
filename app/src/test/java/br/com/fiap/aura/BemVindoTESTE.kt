package br.com.fiap.aura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface

import androidx.compose.ui.graphics.Color
import br.com.fiap.aura.screens.*
import br.com.fiap.aura.ui.theme.AuraTheme

class BemVindoTESTE : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraTheme { // <- use o nome real do seu tema aqui
                Surface(color = Color(0xFFF5F5F5)) {
                   BemVindoScreen()
                }
            }
        }
    }
}
