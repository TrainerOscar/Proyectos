package com.oscar.apihospitalfirebase.screen

import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.ui.dialog.FormularioMedicoDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorScreen(viewModel: MedicoViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var medicos by remember { mutableStateOf<List<Medico>>(emptyList()) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(Unit) {
        viewModel.obtenerMedicos { medicos = it }
    }

    val filteredMedicos = medicos.filter {
        it.nombre.contains(searchQuery.text, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "👨‍⚕️ Gestión de Médicos",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Médico")
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
                items(filteredMedicos) { medico ->
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
                                Text("Nombre: ${medico.nombre}", fontWeight = FontWeight.SemiBold)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                Icon(Icons.Filled.MedicalServices, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Especialidad: ${medico.especialidad}")
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Horario: ${medico.horario}")
                            Spacer(modifier = Modifier.height(2.dp))
                            Text("Sala: ${medico.sala}")
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioMedicoDialog(
            medico = null,
            onDismiss = { showDialog = false },
            onGuardar = { nuevoMedico ->
                viewModel.guardarMedico(
                    nuevoMedico,
                    onSuccess = { showDialog = false },
                    onError = { e -> Log.e("DoctorScreen", "Error al guardar: ${e.message}") }
                )
            }
        )
    }
}
