package com.jovani.sicenet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jovani.sicenet.ui.login.LoginScreen
import com.jovani.sicenet.ui.login.LoginViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jovani.sicenet.ui.perfil.PerfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel: LoginViewModel = viewModel()

            var currentScreen by remember { mutableStateOf("login") }

            if (currentScreen == "login") {
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = { currentScreen = "perfil" }
                )
            } else {
                PerfilScreen(datos = "Bienvenido a SICENET.")
            }
        }
    }
}