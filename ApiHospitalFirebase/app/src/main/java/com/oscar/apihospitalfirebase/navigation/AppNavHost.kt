package com.oscar.apihospitalfirebase.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.screen.*
import com.oscar.apihospitalfirebase.viewmodel.CitaViewModel
import com.oscar.apihospitalfirebase.viewmodel.DiagnosticoViewModel
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    recetaViewModel: RecetaViewModel,
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "pacientes",
        modifier = modifier
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
                recetaViewModel = recetaViewModel,
                pacientesExternos = pacientes,
                medicosExternos = medicos
            )
        }
    }
}
