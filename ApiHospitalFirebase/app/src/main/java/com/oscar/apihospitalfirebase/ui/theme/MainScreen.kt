package com.oscar.apihospitalfirebase.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.navigation.AppNavHost
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    recetaViewModel: RecetaViewModel,
    pacientes: List<Paciente>,
    medicos: List<Medico>
) {
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
