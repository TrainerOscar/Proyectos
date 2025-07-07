package com.oscar.apihospitalfirebase.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.viewmodel.CitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(viewModel: CitaViewModel = viewModel()) {
    var citas by remember { mutableStateOf<List<Cita>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.obtenerCitas { citas = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("📅 Control de Citas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(8.dp)) {
            if (citas.isEmpty()) {
                Text("No hay citas registradas.")
            } else {
                LazyColumn {
                    items(citas) { cita ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color(0xFF6200EE))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Fecha: ${cita.fecha}", fontWeight = FontWeight.SemiBold)
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Hora: ${cita.hora}")
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("Paciente ID: ${cita.pacienteId}") // nombrePaciente aún no disponible
                                Spacer(modifier = Modifier.height(8.dp))
                                EstadoCitaBotones(cita, viewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EstadoCitaBotones(cita: Cita, viewModel: CitaViewModel) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                actualizarEstadoCita(cita, "Aceptada", viewModel)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("Aceptar")
        }

        Button(
            onClick = {
                actualizarEstadoCita(cita, "En espera", viewModel)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC107))
        ) {
            Text("Esperar")
        }

        Button(
            onClick = {
                actualizarEstadoCita(cita, "Rechazada", viewModel)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
        ) {
            Text("Rechazar")
        }
    }
}

// Función auxiliar para actualizar estado
fun actualizarEstadoCita(cita: Cita, nuevoEstado: String, viewModel: CitaViewModel) {
    val citaActualizada = cita.copy(estado = nuevoEstado)
    viewModel.actualizarCita(citaActualizada,
        onSuccess = { Log.d("AppointmentScreen", "Estado actualizado a $nuevoEstado") },
        onError = { e -> Log.e("AppointmentScreen", "Error: ${e.message}") }
    )
}
