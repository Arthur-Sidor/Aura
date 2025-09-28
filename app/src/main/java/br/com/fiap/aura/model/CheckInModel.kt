package br.com.fiap.aura.model

data class CheckInModel(
    val usuarioId: String = "anonimo",
    val humor: String,
    val descricao: String,
)
