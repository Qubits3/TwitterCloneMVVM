package com.example.twitterclonemvvm.model

data class Auth(
    val email: String?,
    val password: String?,
    val returnSecureToken: Boolean = true
)
