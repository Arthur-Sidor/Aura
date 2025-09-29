package br.com.fiap.aura.model

data class CargaModel(
    val carga: String,
    val impacto: String,
    val horasExtras: String,
    val data: String // Pode trocar depois por LocalDate ou Instant
)
