package com.oscar.apihospitalfirebase.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.ui.dialog.FormularioCitaDialog
import com.oscar.apihospitalfirebase.viewmodel.CitaViewModel
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(
    citaViewModel: CitaViewModel = viewModel(),
    pacienteViewModel: PacienteViewModel = viewModel(),
    medicoViewModel: MedicoViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var citas by remember { mutableStateOf<List<Cita>>(emptyList()) }
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }
    var medicos by remember { mutableStateOf<List<Medico>>(emptyList()) }

    fun cargarDatos() {
        citaViewModel.obtenerCitas { citas = it }
        pacienteViewModel.obtenerPacientes { pacientes = it }
        medicoViewModel.obtenerMedicos { medicos = it }
    }

    LaunchedEffect(Unit) {
        cargarDatos()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Citas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Cita")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(citas) { cita ->
                val pacienteNombre = pacientes.find { it.id == cita.pacienteId }?.nombre ?: "Paciente no encontrado"
                val medicoNombre = medicos.find { it.id == cita.medicoId }?.nombre ?: "Médico no encontrado"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Paciente: $pacienteNombre")
                        Text(text = "Médico: $medicoNombre")
                        Text(text = "Fecha: ${cita.fecha}")
                        Text(text = "Hora: ${cita.hora}")
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioCitaDialog(
            cita = null,
            pacientes = pacientes,
            medicos = medicos,
            onDismiss = { showDialog = false },
            onGuardar = { nuevaCita ->
                citaViewModel.guardarCita(
                    nuevaCita,
                    onSuccess = {
                        showDialog = false
                        cargarDatos() // Recargar lista
                    },
                    onError = { e -> Log.e("AppointmentScreen", "Error al guardar cita: ${e.message}") }
                )
            }
        )
    }
}
