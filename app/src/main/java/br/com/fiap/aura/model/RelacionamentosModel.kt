package br.com.fiap.aura.model

data class RelacionamentosModel(
    val id: String,
    val tipo: String,
    val mapeando: String,
    val texto: String,
    val respostas: Map<String, String>
)

data class ResponseRelacionamentosModel(
    val perguntaId: String,
    val resposta: String,
    val idModelReport: String,
    val idsResponse: List<String>
)
