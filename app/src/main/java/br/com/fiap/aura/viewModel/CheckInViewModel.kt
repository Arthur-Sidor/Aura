package br.com.fiap.aura.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.CheckInModel
import br.com.fiap.aura.Repository.CheckInRepository

import kotlinx.coroutines.launch

class CheckInViewModel(
    private val repository: CheckInRepository = CheckInRepository()
) : ViewModel() {

    val humorSelecionado = mutableStateOf<String?>(null)
    val descricao = mutableStateOf("")
    val checkInHistory = mutableStateListOf<CheckInModel>()
    val snackbarMessage = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    // Enviar novo check-in
    fun enviarCheckIn() {
        val humor = humorSelecionado.value
        val desc = descricao.value

        if (humor.isNullOrEmpty() || desc.isBlank()) {
            snackbarMessage.value = "Preencha todos os campos"
            return
        }

        viewModelScope.launch {
            try {
                isLoading.value = true
                val checkIn = CheckInModel(
                    humor = humor,
                    descricao = desc
                )
                val response = repository.enviarCheckin(checkIn)
                if (response.isSuccessful) {
                    snackbarMessage.value = "Check-in salvo com sucesso!"
                    humorSelecionado.value = null
                    descricao.value = ""
                    // Atualiza histórico chamando do backend
                    listarCheckIns()
                } else {
                    snackbarMessage.value = "Erro ao salvar check-in."
                }
            } catch (e: Exception) {
                snackbarMessage.value = "Falha na conexão: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    // Buscar histórico de check-ins
    fun listarCheckIns() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = repository.listarCheckins()
                if (response.isSuccessful) {
                    checkInHistory.clear()
                    response.body()?.let { checkInHistory.addAll(it) }
                } else {
                    snackbarMessage.value = "Erro ao carregar histórico."
                }
            } catch (e: Exception) {
                snackbarMessage.value = "Falha na conexão: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}