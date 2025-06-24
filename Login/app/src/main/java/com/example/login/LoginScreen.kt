package com.example.login


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.login.models.LoginViewModel



@Composable
fun LoginScreen(loginViewModel: LoginViewModel, onLoginSuccess: () -> Unit) {
    val context = LocalContext.current

    var usuario by remember { mutableStateOf("")}
    var contrasena by remember { mutableStateOf("")}
    var mostrarContrasena by remember { mutableStateOf("") }
    var errorCredenciales by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
                horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(text = "Iniciar Sesion", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
                errorCredenciales = false
            },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                loginViewModel.login(usuario, contrasena)
                if (loginViewModel.isLoggedIn) {
                    onLoginSuccess()
                } else {

                    Toast.makeText(
                        context,
                        "Usuario o contrasena incorrectos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text ("Iniciar Sesion")
        }
    }
}