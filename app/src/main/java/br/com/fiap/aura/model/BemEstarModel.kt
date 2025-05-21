package br.com.fiap.aura.model

data class BemEstarModel(
    val humor: String,
    val descricao: String,
    val dataHora: Long = System.currentTimeMillis() // Para registro de quando o check-in foi feito
)