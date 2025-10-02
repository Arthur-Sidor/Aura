package br.com.fiap.aura

import br.com.fiap.aura.screens.BemEstarScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.fiap.aura.ui.theme.AuraTheme

class BemEstarTESTE : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuraTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    BemEstarScreen()
                }
            }
        }
    }
}