package com.example.grocelist.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocelist.data.User
import com.example.grocelist.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var uiState by mutableStateOf<RegisterUiState>(RegisterUiState.Idle)
        private set

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    fun register() {
        viewModelScope.launch {
            if (!validateFields()) return@launch

            val emailExists = repository.emailExists(email)
            if (emailExists) {
                uiState = RegisterUiState.Error("Email already being used")
                return@launch
            }

            val user = User(name, email, password)
            val userId = withContext(Dispatchers.IO) { repository.insert(user) }
            uiState = RegisterUiState.Success(user.copy(id = userId))
        }
    }

    private fun validateFields(): Boolean {
        if (name.isEmpty()) {
            uiState = RegisterUiState.Error("")
            return false
        }

        if (email.isEmpty()) {
            uiState = RegisterUiState.Error("")
            return false
        }

        if (password.isEmpty()) {
            uiState = RegisterUiState.Error("")
            return false
        }

        return true
    }

}

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    data class Success(val user: User) : RegisterUiState()
    data class Error(val message: String): RegisterUiState()
}
