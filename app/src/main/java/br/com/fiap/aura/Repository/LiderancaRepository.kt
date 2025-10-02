package br.com.fiap.aura.Repository

import br.com.fiap.aura.api.RetrofitClient
import br.com.fiap.aura.model.LiderancaModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LiderancaRepository {

    // Histórico local em memória
    private val _avaliacoes = MutableStateFlow<List<LiderancaModel>>(emptyList())
    val avaliacoes: StateFlow<List<LiderancaModel>> = _avaliacoes

    private val comunicacaoApi = RetrofitClient.comunicacaoApi

    // Salva a avaliação local e tenta enviar para API
    suspend fun salvarAvaliacao(avaliacao: LiderancaModel) {
        // Atualiza memória local
        _avaliacoes.update { it + avaliacao }

        // Tenta enviar para API
        try {
            val response = comunicacaoApi.enviarResposta(avaliacao)
            if (!response.isSuccessful) {
                // Aqui você pode tratar erro, log, salvar offline etc
            }
        } catch (e: Exception) {
            // Erro de rede, manter local e logar
            e.printStackTrace()
        }
    }

    // Busca histórico do servidor
    suspend fun buscarHistorico() {
        try {
            val response = comunicacaoApi.getHistorico()
            if (response.isSuccessful) {
                response.body()?.let { historico ->
                    _avaliacoes.value = historico
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun obterUltimaAvaliacao(): LiderancaModel? {
        return _avaliacoes.value.lastOrNull()
    }

    // Cálculo de média para qualquer campo numérico (interesse, disponibilidade, conforto, reconhecimento)
    fun calcularMedia(selector: (LiderancaModel) -> Int): Double {
        val lista = _avaliacoes.value
        if (lista.isEmpty()) return 0.0
        return lista.map(selector).average()
    }

    fun totalAvaliacoes(): Int = _avaliacoes.value.size

    fun limparAvaliacoes() {
        _avaliacoes.value = emptyList()
    }
}
