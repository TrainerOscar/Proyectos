package com.oscar.apihospitalfirebase.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel

@Composable
fun GestorPacientesScreen(viewModel: PacienteViewModel = viewModel()) {
    var pacientes by remember { mutableStateOf(listOf<Paciente>()) }
    var showDialog by remember { mutableStateOf(false) }
    var pacienteSeleccionado by remember { mutableStateOf<Paciente?>(null) }

    // Obtener pacientes
    LaunchedEffect(Unit) {
        viewModel.obtenerPacientes { pacientes = it }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Gestor de Pacientes", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            pacienteSeleccionado = null
            showDialog = true
        }) {
            Text("Registrar nuevo paciente")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(pacientes) { paciente ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Nombre: ${paciente.nombre}", style = MaterialTheme.typography.bodyLarge)
                        Text("Edad: ${paciente.edad}", style = MaterialTheme.typography.bodyMedium)
                        Text("Cita: ${paciente.fechaCita} a las ${paciente.horaCita}", style = MaterialTheme.typography.bodyMedium)

                        Row(modifier = Modifier.padding(top = 8.dp)) {
                            OutlinedButton(onClick = {
                                pacienteSeleccionado = paciente
                                showDialog = true
                            }) {
                                Text("Editar")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            OutlinedButton(onClick = {
                                viewModel.eliminarPaciente(paciente.id)
                                viewModel.obtenerPacientes { pacientes = it }
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
        FormularioPacienteDialog(
            paciente = pacienteSeleccionado,
            onDismiss = { showDialog = false },
            onSave = { pacienteNuevo ->
                if (pacienteSeleccionado == null) {
                    viewModel.guardarPaciente(pacienteNuevo)
                } else {
                    viewModel.actualizarPaciente(pacienteNuevo)
                }
                viewModel.obtenerPacientes { pacientes = it }
                showDialog = false
            }
        )
    }
}
