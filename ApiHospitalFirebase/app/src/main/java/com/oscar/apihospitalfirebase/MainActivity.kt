package com.oscar.apihospitalfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
            // ViewModels
            val pacienteViewModel: PacienteViewModel = viewModels<PacienteViewModel>().value
            val medicoViewModel: MedicoViewModel = viewModels<MedicoViewModel>().value
            val recetaViewModel: RecetaViewModel = viewModels<RecetaViewModel>().value

            // Estados de pacientes y médicos compartidos
            val pacientes = remember { mutableStateListOf<Paciente>() }
            val medicos = remember { mutableStateListOf<Medico>() }

            // Cargar datos
            pacienteViewModel.obtenerPacientes {
                pacientes.clear()
                pacientes.addAll(it)
            }

            medicoViewModel.obtenerMedicos {
                medicos.clear()
                medicos.addAll(it)
            }

            // Navegación
            val navController = rememberNavController()

            // Pantalla principal
            MainScreen(
                navController = navController,
                recetaViewModel = recetaViewModel,
                pacientes = pacientes,
                medicos = medicos
            )
        }
    }
}
