package br.com.fiap.aura.model

data class BemEstarRequestModel(
    val usuarioId: String = "anonimo",
    val humor: String,
    val descricao: String
)
