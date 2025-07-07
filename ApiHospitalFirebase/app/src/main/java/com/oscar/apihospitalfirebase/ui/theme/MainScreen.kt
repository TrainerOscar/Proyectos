package com.oscar.apihospitalfirebase.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.oscar.apihospitalfirebase.navigation.AppNavHost
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

@Composable
fun MainScreen(
    navController: NavHostController
) {
    val recetaViewModel: RecetaViewModel = viewModel()
    val pacienteViewModel: PacienteViewModel = viewModel()
    val medicoViewModel: MedicoViewModel = viewModel()

    var pacientes by remember { mutableStateOf(emptyList<com.oscar.apihospitalfirebase.model.Paciente>()) }
    var medicos by remember { mutableStateOf(emptyList<com.oscar.apihospitalfirebase.model.Medico>()) }

    LaunchedEffect(Unit) {
        pacienteViewModel.obtenerPacientes { pacientes = it }
        medicoViewModel.obtenerMedicos { medicos = it }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("pacientes") },
                    label = { Text("Pacientes") },
                    icon = { Icon(Icons.Filled.Person, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("medicos") },
                    label = { Text("Médicos") },
                    icon = { Icon(Icons.Filled.MedicalServices, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("citas") },
                    label = { Text("Citas") },
                    icon = { Icon(Icons.Filled.CalendarToday, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("diagnosticos") },
                    label = { Text("Diagnóstico") },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("recetas") },
                    label = { Text("Recetas") },
                    icon = { Icon(Icons.Filled.Receipt, contentDescription = null) }
                )
            }
        }
    ) { padding ->
        AppNavHost(
            navController = navController,
            recetaViewModel = recetaViewModel,
            pacientes = pacientes,
            medicos = medicos,
            modifier = Modifier.padding(padding)
        )
    }
}
