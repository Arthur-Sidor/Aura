package br.com.fiap.aura.Viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.com.fiap.aura.model.RelacionamentosModel
import br.com.fiap.aura.model.ResponseRelacionamentosModel

class RelacionamentosViewModel : ViewModel() {

    val relacionamentos = mutableStateListOf<RelacionamentosModel>()
    val respostasUsuario = mutableStateListOf<ResponseRelacionamentosModel>()

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)

    // Carregar perguntas fixas (mock)
    fun carregarRelacionamentos() {
        isLoading.value = true
        errorMessage.value = null
        try {
            val listaMock = listOf(
                RelacionamentosModel("1", "Escala 1-5", "Chefe", "Como está o seu relacionamento com seu chefe numa escala de 1 a 5? (Sendo 1 - ruim e 5 - ótimo)", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("2", "Escala 1-5", "Colegas", "Como está o seu relacionamento com seus colegas de trabalho numa escala de 1 a 5?", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("3", "Escala 1-5", "Respeito", "Sinto que sou tratado(a) com respeito pelos meus colegas de trabalho.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("4", "Escala 1-5", "Colaboração", "Consigo me relacionar de forma saudável e colaborativa com minha equipe.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("5", "Escala 1-5", "Opinião", "Tenho liberdade para expressar minhas opiniões sem medo de retaliações.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("6", "Escala 1-5", "Acolhimento", "Me sinto acolhido(a) a parte do time onde trabalho.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("7", "Escala 1-5", "Cooperação", "Sinto que existe espírito de cooperação entre os colaboradores.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5")),
                RelacionamentosModel("8", "Escala 1-5", "Orientações", "Recebo orientações claras e objetivas sobre minhas atividades e responsabilidades.", mapOf("1" to "1", "2" to "2", "3" to "3", "4" to "4", "5" to "5"))
            )
            relacionamentos.clear()
            relacionamentos.addAll(listaMock)
        } catch (e: Exception) {
            errorMessage.value = e.message
        } finally {
            isLoading.value = false
        }
    }

    // Salvar uma resposta do usuário
    fun salvarResposta(response: ResponseRelacionamentosModel) {
        val index = respostasUsuario.indexOfFirst { it.perguntaId == response.perguntaId }
        if (index >= 0) {
            respostasUsuario[index] = response
        } else {
            respostasUsuario.add(response)
        }
    }

    // Limpar todas as respostas
    fun limparRespostas() {
        respostasUsuario.clear()
    }

    // Calcular média das respostas
    fun calcularMedia(): Double {
        if (respostasUsuario.isEmpty()) return 0.0
        val soma = respostasUsuario.mapNotNull { it.resposta.toIntOrNull() }.sum()
        return soma.toDouble() / respostasUsuario.size
    }

    // Função de enviar respostas (ex: pode chamar API aqui)
    fun enviarRespostas(
        onSuccess: (media: Double) -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        try {
            if (respostasUsuario.isEmpty()) {
                onError("Nenhuma resposta selecionada")
                return
            }

            val media = calcularMedia()
            onSuccess(media)

            // Opcional: limpar respostas após envio
            // limparRespostas()

        } catch (e: Exception) {
            onError(e.message ?: "Erro desconhecido")
        }
    }
}
