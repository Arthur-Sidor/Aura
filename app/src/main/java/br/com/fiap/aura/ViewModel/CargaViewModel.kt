package br.com.fiap.aura.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.CargaModel
import br.com.fiap.aura.repository.CargaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CargaViewModel(
    private val repository: CargaRepository = CargaRepository()
) : ViewModel() {

    // Estados do formulário
    private val _cargaSelecionada = MutableStateFlow("")
    val cargaSelecionada: StateFlow<String> = _cargaSelecionada

    private val _impactoSelecionado = MutableStateFlow("")
    val impactoSelecionado: StateFlow<String> = _impactoSelecionado

    private val _horasExtrasSelecionada = MutableStateFlow("")
    val horasExtrasSelecionada: StateFlow<String> = _horasExtrasSelecionada

    val historico: StateFlow<List<CargaModel>> = repository.avaliacoes

    fun atualizarCarga(valor: String) {
        _cargaSelecionada.value = valor
    }

    fun atualizarImpacto(valor: String) {
        _impactoSelecionado.value = valor
    }

    fun atualizarHorasExtras(valor: String) {
        _horasExtrasSelecionada.value = valor
    }

    fun registrarAvaliacao() {
        val novaAvaliacao = CargaModel(
            carga = _cargaSelecionada.value,
            impacto = _impactoSelecionado.value,
            horasExtras = _horasExtrasSelecionada.value,
            data = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        )

        viewModelScope.launch {
            repository.salvarAvaliacao(novaAvaliacao)
        }
    }

    fun carregarHistorico() {
        viewModelScope.launch {
            repository.buscarHistorico()
        }
    }

    fun limparHistorico() {
        repository.limparAvaliacoes()
    }

    fun mediaCarga(): String = repository.calcularMediaCarga()
    fun totalAvaliacoes(): Int = repository.totalAvaliacoes()
    fun ultimaAvaliacao(): CargaModel? = repository.obterUltimaAvaliacao()
}
