package br.com.fiap.aura.model

data class LiderancaModel(
    val interesseBemEstar: Int,       // Pergunta: Minha liderança demonstra interesse pelo meu bem-estar
    val disponibilidadeOuvir: Int,   // Pergunta: Minha liderança está disponível para me ouvir
    val confortoReportar: Int,        // Pergunta: Me sinto confortável para reportar problemas
    val reconhecimento: Int,          // Pergunta: Minha liderança reconhece minhas entregas
    val data: String                  // Registrar o dia (pode trocar depois por LocalDate ou Instant)
)
