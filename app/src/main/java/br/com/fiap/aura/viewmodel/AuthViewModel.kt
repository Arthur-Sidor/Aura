package br.com.fiap.aura.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.aura.model.auth.RegisterRequestModel
import br.com.fiap.aura.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val useMock: Boolean = true // true = mock, false = backend real
) : ViewModel() {

    private val repository = AuthRepository()

    private val _loginResult = MutableStateFlow<String?>(null)
    val loginResult: StateFlow<String?> = _loginResult

    private val _registerResult = MutableStateFlow<String?>(null)
    val registerResult: StateFlow<String?> = _registerResult

    // Login
    fun login(username: String, password: String) {
        viewModelScope.launch {
            if (useMock) {
                // Simula login bem-sucedido
                _loginResult.value = "ey.mock.jwt.token"
            } else {
                val response = repository.login(username, password)
                if (response.isSuccessful) {
                    _loginResult.value = response.body()?.token ?: "Token não encontrado"
                } else {
                    _loginResult.value = "Erro: ${response.errorBody()?.string()}"
                }
            }
        }
    }

    // Cadastro
    fun register(request: RegisterRequestModel) {
        viewModelScope.launch {
            if (useMock) {
                // Simula cadastro bem-sucedido
                _registerResult.value = "ey.mock.jwt.token"
            } else {
                val response = repository.register(request)
                if (response.isSuccessful) {
                    _registerResult.value = "Usuário cadastrado com id: ${response.body()?.id}"
                } else {
                    _registerResult.value = "Erro: ${response.errorBody()?.string()}"
                }
            }
        }
    }
}
