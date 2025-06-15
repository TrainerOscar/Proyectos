package com.oscar.apihospitalfirebase.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.viewmodel.CitaViewModel
import com.oscar.apihospitalfirebase.screen.FormularioCitaDialog

@Composable
fun GestorCitasScreen(viewModel: CitaViewModel = viewModel()) {
    var citas by remember { mutableStateOf(emptyList<Cita>()) }
    var showDialog by remember { mutableStateOf(false) }
    var citaSeleccionada by remember { mutableStateOf<Cita?>(null) }

    // Filtros
    var filtroPacienteId by remember { mutableStateOf("") }
    var filtroMedicoId by remember { mutableStateOf("") }

    val citasFiltradas = citas.filter {
        (filtroPacienteId.isBlank() || it.pacienteId.contains(filtroPacienteId, ignoreCase = true)) &&
                (filtroMedicoId.isBlank() || it.medicoId.contains(filtroMedicoId, ignoreCase = true))
    }

    val totalCitas = citasFiltradas.size

    LaunchedEffect(Unit) {
        viewModel.obtenerCitas { citas = it }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gestor de Citas", style = MaterialTheme.typography.headlineMedium)
        Text("Total de Citas: $totalCitas", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = filtroPacienteId,
            onValueChange = { filtroPacienteId = it },
            label = { Text("Filtrar por ID de Paciente") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = filtroMedicoId,
            onValueChange = { filtroMedicoId = it },
            label = { Text("Filtrar por ID de Médico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            citaSeleccionada = null
            showDialog = true
        }) {
            Text("Registrar Nueva Cita")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(citasFiltradas) { cita ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Paciente ID: ${cita.pacienteId}", style = MaterialTheme.typography.bodyLarge)
                        Text("Médico ID: ${cita.medicoId}", style = MaterialTheme.typography.bodyLarge)
                        Text("Fecha: ${cita.fecha}", style = MaterialTheme.typography.bodyMedium)
                        Text("Hora: ${cita.hora}", style = MaterialTheme.typography.bodyMedium)

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = {
                                citaSeleccionada = cita
                                showDialog = true
                            }) {
                                Text("Editar")
                            }
                            TextButton(onClick = {
                                viewModel.eliminarCita(cita.id)
                                viewModel.obtenerCitas { citas = it }
                            }) {
                                Text("Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioCitaDialog(
            cita = citaSeleccionada,
            onDismiss = { showDialog = false },
            onGuardar = { citaNueva ->
                if (citaSeleccionada == null) {
                    viewModel.guardarCita(citaNueva)
                } else {
                    viewModel.actualizarCita(citaNueva)
                }
                viewModel.obtenerCitas { citas = it }
                showDialog = false
            }
        )
    }
}
