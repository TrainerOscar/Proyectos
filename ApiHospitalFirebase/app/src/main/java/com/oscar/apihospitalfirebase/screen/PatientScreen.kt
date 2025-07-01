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
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.ui.dialog.FormularioPacienteDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientScreen(viewModel: PacienteViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.obtenerPacientes { pacientes = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pacientes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Paciente")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(pacientes) { paciente ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nombre: ${paciente.nombre}")
                        Text(text = "Teléfono: ${paciente.telefono}")
                        Text(text = "Motivo: ${paciente.motivo}")
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
