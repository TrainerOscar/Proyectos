package com.oscar.apihospitalfirebase

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.google.firebase.FirebaseApp
import com.oscar.apihospitalfirebase.screen.GestorPacientesScreen
import com.oscar.apihospitalfirebase.ui.theme.ApiHospitalFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa Firebase
        FirebaseApp.initializeApp(this)

        setContent {
            ApiHospitalFirebaseTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Pantalla principal que se mostrará al iniciar
                    GestorPacientesScreen()
                }
            }
        }
    }
}