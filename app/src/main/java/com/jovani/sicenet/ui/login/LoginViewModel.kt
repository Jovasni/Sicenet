package com.jovani.sicenet.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovani.sicenet.data.repository.SicenetRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = SicenetRepository()

    var matricula by mutableStateOf("")
    var contrasenia by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var loginResult by mutableStateOf("")
    var isSuccess by mutableStateOf(false)

    fun onLoginClick() {
        if (matricula.isBlank() || contrasenia.isBlank()) {
            loginResult = "Por favor llena todos los campos"
            return
        }

        viewModelScope.launch {
            isLoading = true
            val result = repository.login(matricula, contrasenia)

            if (result != null && result.contains("accesoLoginResult")) {
                loginResult = "¡Éxito! Cargando perfil..."
                isSuccess = true
            } else {
                loginResult = "Error: Usuario o contraseña incorrectos"
            }
            isLoading = false
        }
    }
}