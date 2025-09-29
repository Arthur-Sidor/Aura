package br.com.fiap.aura.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.CheckInModel
import br.com.fiap.aura.repository.CheckInRepository
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val repository: CheckInRepository = CheckInRepository()
) : ViewModel() {

    val humorSelecionado = mutableStateOf<String?>(null)
    val descricao = mutableStateOf("")
    val checkInHistory = mutableStateListOf<CheckInModel>()
    val snackbarMessage = mutableStateOf<String?>(null)

    fun enviarCheckIn() {
        val humor = humorSelecionado.value
        val desc = descricao.value

        if (humor.isNullOrEmpty() || desc.isBlank()) {
            snackbarMessage.value = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            try {
                val checkIn = CheckInModel(
                    humor = humor,
                    descricao = desc
                )
                val response = repository.enviarCheckin(checkIn)
                if (response.isSuccessful) {
                    checkInHistory.add(checkIn)
                    snackbarMessage.value = "Check-in salvo com sucesso!"
                    humorSelecionado.value = null
                    descricao.value = ""
                } else {
                    snackbarMessage.value = "Erro ao salvar check-in."
                }
            } catch (e: Exception) {
                snackbarMessage.value = "Falha na conexão: ${e.message}"
            }
        }
    }
}
