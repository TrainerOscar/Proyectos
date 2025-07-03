package com.oscar.apihospitalfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.rememberNavController
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.ui.MainScreen
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val pacienteViewModel: PacienteViewModel = viewModels<PacienteViewModel>().value
            val medicoViewModel: MedicoViewModel = viewModels<MedicoViewModel>().value
            val recetaViewModel: RecetaViewModel = viewModels<RecetaViewModel>().value

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

            // Crear navController
            val navController = rememberNavController()

            // Llamar pantalla principal
            MainScreen(
                navController = navController,
                recetaViewModel = recetaViewModel,
                pacientes = pacientes,
                medicos = medicos
            )
        }
    }
}
