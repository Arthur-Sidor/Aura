package br.com.fiap.aura.Viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import br.com.fiap.aura.model.AlertasModel
import br.com.fiap.aura.Repository.AlertasRepository

import kotlinx.coroutines.launch

class AlertasViewModel(
    private val repository: AlertasRepository = AlertasRepository()
) : ViewModel() {

    var respostasSelecionadas = mutableStateOf<List<AlertasModel>>(emptyList())
        private set

    var historico = mutableStateOf<List<List<AlertasModel>>>(emptyList())
        private set

    // Atualiza ou adiciona resposta
    fun atualizarResposta(pergunta: String, resposta: String) {
        val lista = respostasSelecionadas.value.toMutableList()
        lista.removeAll { it.pergunta == pergunta }
        lista.add(AlertasModel(pergunta, resposta))
        respostasSelecionadas.value = lista
    }

    // Calcula média de pontuação de uma avaliação
    fun calcularMedia(avaliacao: List<AlertasModel>): Double {
        val mapPontuacao = mapOf(
            "Nunca" to 0,
            "Raramente" to 1,
            "Às vezes" to 2,
            "Frequentemente" to 3,
            "Sempre" to 4
        )
        if (avaliacao.isEmpty()) return 0.0
        val total = avaliacao.sumOf { mapPontuacao[it.resposta] ?: 0 }
        return total.toDouble() / avaliacao.size
    }

    // Converte média em nível de risco
    fun nivelRisco(media: Double): String {
        return when {
            media <= 1.0 -> "Baixo"
            media <= 2.5 -> "Médio"
            else -> "Alto"
        }
    }

    fun finalizarAvaliação() {
        viewModelScope.launch {
            repository.enviarAlertas(respostasSelecionadas.value)
            carregarHistorico()
            respostasSelecionadas.value = emptyList() // limpa formulário
        }
    }

    fun carregarHistorico() {
        viewModelScope.launch {
            historico.value = repository.listarAlertas()
        }
    }
}
