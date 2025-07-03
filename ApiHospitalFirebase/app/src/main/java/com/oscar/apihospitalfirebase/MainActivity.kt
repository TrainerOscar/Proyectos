package com.oscar.apihospitalfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.ui.MainScreen
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pacienteViewModel = PacienteViewModel()
            val medicoViewModel = MedicoViewModel()
            val recetaViewModel = RecetaViewModel()

            val pacientes = remember { mutableStateListOf<Paciente>() }
            val medicos = remember { mutableStateListOf<Medico>() }

            // Cargar pacientes
            pacienteViewModel.obtenerPacientes {
                pacientes.clear()
                pacientes.addAll(it)
            }

            // Cargar médicos
            medicoViewModel.obtenerMedicos {
                medicos.clear()
                medicos.addAll(it)
            }

            // Llamar a la pantalla principal
            MainScreen(
                recetaViewModel = recetaViewModel,
                pacientes = pacientes,
                medicos = medicos
            )
        }
    }
}
