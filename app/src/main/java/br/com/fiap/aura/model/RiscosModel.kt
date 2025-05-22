package br.com.fiap.aura.model



data class AvaliacaoRiscosModelResposta(
    val pergunta: String,
    val resposta: String
)

data class AvaliacaoRiscosRequest(
    val usuarioId: String = "anonimo",
    val respostas: List<AvaliacaoRiscosModelResposta>
)