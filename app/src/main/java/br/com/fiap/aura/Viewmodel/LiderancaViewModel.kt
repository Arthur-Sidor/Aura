package br.com.fiap.aura.Viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.LiderancaModel
import br.com.fiap.aura.Repository.LiderancaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class LiderancaViewModel(
    private val repository: LiderancaRepository = LiderancaRepository()
) : ViewModel() {

    // Estados do formulário (valor inicial 3 é mais neutro para UI)
    private val _interesseBemEstar = MutableStateFlow(0)
    val interesseBemEstar: StateFlow<Int> = _interesseBemEstar.asStateFlow()

    private val _disponibilidadeOuvir = MutableStateFlow(0)
    val disponibilidadeOuvir: StateFlow<Int> = _disponibilidadeOuvir.asStateFlow()

    private val _confortoReportar = MutableStateFlow(0)
    val confortoReportar: StateFlow<Int> = _confortoReportar.asStateFlow()

    private val _reconhecimento = MutableStateFlow(0)
    val reconhecimento: StateFlow<Int> = _reconhecimento.asStateFlow()

    // Histórico de avaliações
    val historico: StateFlow<List<LiderancaModel>> = repository.avaliacoes

    // --- NOVA FUNCIONALIDADE: Média Geral ---
    // Este StateFlow calcula a média geral de todas as respostas do histórico.
    // Ele será atualizado automaticamente sempre que 'historico' mudar.
    val mediaGeral: StateFlow<Double> = historico.map { listaDeAvaliacoes ->
        if (listaDeAvaliacoes.isEmpty()) {
            0.0
        } else {
            val todasAsRespostas = listaDeAvaliacoes.flatMap {
                listOf(it.interesseBemEstar, it.disponibilidadeOuvir, it.confortoReportar, it.reconhecimento)
            }
            todasAsRespostas.average()
        }
    }.stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), 0.0)

    // Funções de atualização (sem alteração)
    fun atualizarInteresse(valor: Int) { _interesseBemEstar.value = valor }
    fun atualizarDisponibilidade(valor: Int) { _disponibilidadeOuvir.value = valor }
    fun atualizarConforto(valor: Int) { _confortoReportar.value = valor }
    fun atualizarReconhecimento(valor: Int) { _reconhecimento.value = valor }

    // Registrar avaliação
    fun registrarAvaliacao() {
        val respostas = listOf(_interesseBemEstar.value, _disponibilidadeOuvir.value, _confortoReportar.value, _reconhecimento.value)
        // Só registra se pelo menos uma pergunta foi respondida (valor diferente do inicial)
        if (respostas.any { it != 0 }) {
            val novaAvaliacao = LiderancaModel(
                interesseBemEstar = _interesseBemEstar.value,
                disponibilidadeOuvir = _disponibilidadeOuvir.value,
                confortoReportar = _confortoReportar.value,
                reconhecimento = _reconhecimento.value,
                data = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            )
            viewModelScope.launch {
                repository.salvarAvaliacao(novaAvaliacao)
                resetarValores() // Reseta os campos após salvar
            }
        }
    }

    // Função para resetar os campos para o valor inicial
    private fun resetarValores() {
        _interesseBemEstar.value = 0
        _disponibilidadeOuvir.value = 0
        _confortoReportar.value = 0
        _reconhecimento.value = 0
    }

    // Carregar histórico (sem alteração)
    fun carregarHistorico() {
        viewModelScope.launch {
            repository.buscarHistorico()
        }
    }
}
