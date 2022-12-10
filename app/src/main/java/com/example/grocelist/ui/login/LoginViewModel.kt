package com.example.grocelist.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.User
import com.example.grocelist.data.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var uiState by mutableStateOf<LoginUiState>(LoginUiState.Idle)
        private set

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun login() {
        viewModelScope.launch {
            if (!validateFields()) return@launch

            val user = repository.getUserByEmailAndPassword(email, password)

            uiState = if (user != null) {
                LoginUiState.Success(user)
            } else {
                LoginUiState.Error("Usuário não encontrado")
            }
        }
    }

    private fun validateFields(): Boolean {
        if (email.isEmpty() || email.isBlank()) {
            uiState = LoginUiState.Error("Email não pode ser vazio")
            return false
        }

        if (password.isEmpty() || password.isBlank()) {
            uiState = LoginUiState.Error("Senha não pode ser vazia")
            return false
        }

        return true
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    data class Success(val user: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
