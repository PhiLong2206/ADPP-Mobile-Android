package com.adpp.mobile.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adpp.mobile.feature.auth.presentation.state.ForgotPasswordUiState
import com.adpp.mobile.feature.auth.presentation.state.LoginUiState
import com.adpp.mobile.feature.auth.presentation.state.RegisterUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginUiState())
    val loginState: StateFlow<LoginUiState> = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow(RegisterUiState())
    val registerState: StateFlow<RegisterUiState> = _registerState.asStateFlow()

    private val _forgotPasswordState = MutableStateFlow(ForgotPasswordUiState())
    val forgotPasswordState: StateFlow<ForgotPasswordUiState> = _forgotPasswordState.asStateFlow()

    fun updateLoginEmail(email: String) {
        _loginState.update { it.copy(email = email, errorMessage = null) }
    }

    fun updateLoginPassword(password: String) {
        _loginState.update { it.copy(password = password, errorMessage = null) }
    }

    fun toggleRememberMe() {
        _loginState.update { it.copy(rememberMe = !it.rememberMe) }
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loginState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(600) // UI responsiveness feedback
            _loginState.update { it.copy(isLoading = false) }
            onSuccess()
        }
    }

    fun updateRegisterFullName(name: String) {
        _registerState.update { it.copy(fullName = name, errorMessage = null) }
    }

    fun updateRegisterEmail(email: String) {
        _registerState.update { it.copy(email = email, errorMessage = null) }
    }

    fun updateRegisterPassword(password: String) {
        _registerState.update { it.copy(password = password, errorMessage = null) }
    }

    fun updateRegisterConfirmPassword(password: String) {
        _registerState.update { it.copy(confirmPassword = password, errorMessage = null) }
    }

    fun toggleAcceptTerms() {
        _registerState.update { it.copy(acceptedTerms = !it.acceptedTerms) }
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _registerState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(600)
            _registerState.update { it.copy(isLoading = false) }
            onSuccess()
        }
    }

    fun updateForgotEmail(email: String) {
        _forgotPasswordState.update { it.copy(email = email, errorMessage = null) }
    }

    fun submitForgotPassword() {
        viewModelScope.launch {
            _forgotPasswordState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(600)
            _forgotPasswordState.update { it.copy(isLoading = false, isSubmitted = true) }
        }
    }

    fun resetForgotState() {
        _forgotPasswordState.update { ForgotPasswordUiState() }
    }
}

