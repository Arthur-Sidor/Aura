package br.com.fiap.aura.model

data class AvaliacaoRiscosModel(
    val sentimento: String,
    val cargaTrabalho: String,
    val relacaoLideranca: String,
    val sinaisAlerta: String,
    val dataHora: Long = System.currentTimeMillis()
)
