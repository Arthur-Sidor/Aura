// Este import é necessário, mas a tela já parece tê-lo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.ComunicacaoModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Um data class para representar uma avaliação completa com data
data class Avaliacao(
    val data: String,
    val respostas: List<ComunicacaoModel>,
    val media: Double
)

class ComunicacaoViewModel : ViewModel() {

    // 1. Estado para as perguntas atuais na tela
    private val _perguntas = MutableStateFlow<List<ComunicacaoModel>>(emptyList())
    val perguntas: StateFlow<List<ComunicacaoModel>> = _perguntas.asStateFlow()

    // 2. Estado para o histórico de avaliações salvas
    private val _historico = MutableStateFlow<List<Avaliacao>>(emptyList())
    val historico: StateFlow<List<Avaliacao>> = _historico.asStateFlow()

    // 3. Estado que calcula a média geral de todas as avaliações do histórico
    val mediaGeral: StateFlow<Double> = _historico.map { historico ->
        if (historico.isEmpty()) 0.0 else historico.map { it.media }.average()
    }.stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), 0.0)

    init {
        // Carrega as perguntas iniciais quando a ViewModel é criada
        carregarPerguntasIniciais()
    }

    private fun carregarPerguntasIniciais() {
        _perguntas.value = listOf(
            ComunicacaoModel("A comunicação interna da empresa é clara e eficiente", 0),
            ComunicacaoModel("Me sinto informado sobre as decisões importantes", 0),
            ComunicacaoModel("Os canais de comunicação (email, chat) são bem utilizados", 0),
            ComunicacaoModel("Existe abertura para dar feedback construtivo", 0)
        )
    }

    // 4. Função para atualizar a resposta de uma pergunta na tela
    fun atualizarResposta(index: Int, novaResposta: Int) {
        val listaAtual = _perguntas.value.toMutableList()
        if (index in listaAtual.indices) {
            listaAtual[index] = listaAtual[index].copy(resposta = novaResposta)
            _perguntas.value = listaAtual
        }
    }

    // 5. Função para salvar a avaliação atual no histórico e resetar a tela
    fun registrarAvaliacao() {
        viewModelScope.launch {
            val respostasRespondidas = _perguntas.value.filter { it.resposta > 0 }
            if (respostasRespondidas.isNotEmpty()) {
                val mediaDaAvaliacao = respostasRespondidas.map { it.resposta }.average()
                val novaAvaliacao = Avaliacao(
                    data = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date()),
                    respostas = respostasRespondidas,
                    media = mediaDaAvaliacao
                )
                // Adiciona a nova avaliação no topo da lista do histórico
                _historico.value = listOf(novaAvaliacao) + _historico.value
                // Reseta as perguntas para uma nova avaliação
                carregarPerguntasIniciais()
            }
        }
    }
}
