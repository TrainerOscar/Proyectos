package com.oscar.apihospitalfirebase.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
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
    var citas by remember { mutableStateOf<List<Cita>>(emptyList()) }
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }
    var medicos by remember { mutableStateOf<List<Medico>>(emptyList()) }

    LaunchedEffect(Unit) {
        citaViewModel.obtenerCitas { citas = it }
        pacienteViewModel.obtenerPacientes { pacientes = it }
        medicoViewModel.obtenerMedicos { medicos = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📅 Citas Registradas", fontSize = 20.sp) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(8.dp)) {

            if (citas.isEmpty()) {
                Text("No hay citas registradas.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn {
                    items(citas) { cita ->
                        val paciente = pacientes.find { it.id == cita.pacienteId }?.nombre ?: "Desconocido"
                        val medico = medicos.find { it.id == cita.medicoId }?.nombre ?: "Desconocido"

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFECECEC))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Paciente: $paciente", fontSize = 16.sp)
                                Text("Médico: $medico", fontSize = 16.sp)
                                Text("Fecha: ${cita.fecha}")
                                Text("Hora: ${cita.hora}")
                                Text("Estado: ${cita.estado}", fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Button(
                                        onClick = {
                                            val citaActualizada = cita.copy(estado = "Aceptada")
                                            citaViewModel.actualizarCita(citaActualizada) {
                                                citaViewModel.obtenerCitas { citas = it }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                                    ) {
                                        Text("Aceptar")
                                    }

                                    Button(
                                        onClick = {
                                            val citaActualizada = cita.copy(estado = "Rechazada")
                                            citaViewModel.actualizarCita(citaActualizada) {
                                                citaViewModel.obtenerCitas { citas = it }
                                            }
                                        },
                                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                                    ) {
                                        Text("Rechazar")
                                    }

                                    IconButton(onClick = {
                                        citaViewModel.eliminarCita(cita.id) {
                                            citaViewModel.obtenerCitas { citas = it }
                                        }
                                    }) {
                                        Icon(Icons.Filled.Delete, contentDescription = "Eliminar cita")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
