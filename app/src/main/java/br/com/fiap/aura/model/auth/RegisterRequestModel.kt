package br.com.fiap.aura.model.auth

data class RegisterRequestModel(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val username: String,
    val cpf: String
)