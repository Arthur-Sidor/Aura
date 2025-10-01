package br.com.fiap.aura.Repository

import br.com.fiap.aura.api.RetrofitClient
import br.com.fiap.aura.model.CargaModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CargaRepository {

    // Histórico local em memória
    private val _avaliacoes = MutableStateFlow<List<CargaModel>>(emptyList())
    val avaliacoes: StateFlow<List<CargaModel>> = _avaliacoes

    private val cargaApi = RetrofitClient.cargaApi

    // Salva a avaliação local e tenta enviar para API
    suspend fun salvarAvaliacao(avaliacao: CargaModel) {
        // Atualiza memória local
        _avaliacoes.update { it + avaliacao }

        // Tenta enviar para API
        try {
            val response = cargaApi.enviarResposta(avaliacao)
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
            val response = cargaApi.getHistorico()
            if (response.isSuccessful) {
                response.body()?.let { historico ->
                    _avaliacoes.value = historico
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun obterUltimaAvaliacao(): CargaModel? {
        return _avaliacoes.value.lastOrNull()
    }

    fun calcularMediaCarga(): String {
        if (_avaliacoes.value.isEmpty()) return "N/A"

        val contagem = _avaliacoes.value.groupingBy { it.carga }.eachCount()
        return contagem.maxByOrNull { it.value }?.key ?: "N/A"
    }

    fun totalAvaliacoes(): Int = _avaliacoes.value.size

    fun limparAvaliacoes() {
        _avaliacoes.value = emptyList()
    }
}
