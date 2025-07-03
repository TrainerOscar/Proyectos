package com.oscar.apihospitalfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.screen.*
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = "pacientes",
    recetaViewModel: RecetaViewModel,
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("pacientes") {
            PatientScreen()
        }
        composable("medicos") {
            DoctorScreen()
        }
        composable("citas") {
            AppointmentScreen()
        }
        composable("diagnosticos") {
            DiagnosisScreen()
        }
        composable("recetas") {
            PrescriptionScreen(
                recetaViewModel = recetaViewModel
            )
        }
    }
}
