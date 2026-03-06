package com.jovani.sicenet.ui.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.jovani.sicenet.data.repository.SicenetRepository
import com.jovani.sicenet.data.workers.FetchDataWorker
import com.jovani.sicenet.data.workers.SaveDataWorker
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
                loginResult = "Cargando perfil..<."
                isSuccess = true
            } else {
                loginResult = "Error: Usuario o contraseña incorrectos"
            }
            isLoading = false
        }
    }

    fun iniciarSincronizacion(context: Context) {
        val workManager = WorkManager.getInstance(context)

        val requestFetch = OneTimeWorkRequestBuilder<FetchDataWorker>()
            .build()

        val requestSave = OneTimeWorkRequestBuilder<SaveDataWorker>()
            .build()

        workManager.beginUniqueWork(
            "sincronizacion_inicial",
            ExistingWorkPolicy.REPLACE,
            requestFetch
        ).then(requestSave)
            .enqueue()
    }
}