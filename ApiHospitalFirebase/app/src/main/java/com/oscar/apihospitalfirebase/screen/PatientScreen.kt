package com.oscar.apihospitalfirebase.screen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.ui.dialog.FormularioPacienteDialog
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientScreen(viewModel: PacienteViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(Unit) {
        viewModel.obtenerPacientes { pacientes = it }
    }

    val filteredPacientes = pacientes.filter {
        it.nombre.contains(searchQuery.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "👤 Gestión de Pacientes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Paciente")
            }
        }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(8.dp)) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            LazyColumn {
                items(filteredPacientes) { paciente ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                Icon(Icons.Filled.Person, contentDescription = null, tint = Color(0xFF6200EE))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Nombre: ${paciente.nombre}", fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                Icon(Icons.Filled.CalendarToday, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Teléfono: ${paciente.telefono}")
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                Icon(Icons.Filled.MedicalServices, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Motivo: ${paciente.motivo}")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioPacienteDialog(
            paciente = null,
            onDismiss = { showDialog = false },
            onGuardar = { nuevoPaciente ->
                viewModel.guardarPaciente(
                    nuevoPaciente,
                    onSuccess = { showDialog = false },
                    onError = { e -> Log.e("PatientScreen", "Error al guardar: ${e.message}") }
                )
            }
        )
    }
}
