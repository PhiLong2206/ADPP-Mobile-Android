package com.adpp.mobile.feature.auth.presentation.state

data class LoginUiState(
    val email: String = "nguyen.anh@adpp.edu.vn",
    val password: String = "MatKhau123!",
    val isPasswordVisible: Boolean = false,
    val rememberMe: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class RegisterUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val acceptedTerms: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

data class ForgotPasswordUiState(
    val email: String = "",
    val isLoading: Boolean = false,
    val isSubmitted: Boolean = false,
    val errorMessage: String? = null
)
