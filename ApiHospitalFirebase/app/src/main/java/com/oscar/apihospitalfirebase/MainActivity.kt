package com.oscar.apihospitalfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.oscar.apihospitalfirebase.screen.GestorPacientesScreen
import com.oscar.apihospitalfirebase.ui.theme.ApiHospitalFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
