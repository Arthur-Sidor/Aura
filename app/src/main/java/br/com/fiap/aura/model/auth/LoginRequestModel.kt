package br.com.fiap.aura.model.auth

data class LoginRequestModel(
    val username: String,
    val password: String,
)