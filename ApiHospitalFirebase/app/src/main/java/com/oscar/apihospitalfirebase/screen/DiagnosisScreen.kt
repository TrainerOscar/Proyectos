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
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.ui.dialog.FormularioDiagnosticoDialog
import com.oscar.apihospitalfirebase.viewmodel.DiagnosticoViewModel
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiagnosisScreen(
    diagnosticoViewModel: DiagnosticoViewModel = viewModel(),
    pacienteViewModel: PacienteViewModel = viewModel(),
    medicoViewModel: MedicoViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var diagnosticos by remember { mutableStateOf<List<Diagnostico>>(emptyList()) }
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }
    var medicos by remember { mutableStateOf<List<Medico>>(emptyList()) }

    LaunchedEffect(Unit) {
        diagnosticoViewModel.obtenerDiagnosticos { diagnosticos = it }
        pacienteViewModel.obtenerPacientes { pacientes = it }
        medicoViewModel.obtenerMedicos { medicos = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Diagnósticos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Diagnóstico")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(diagnosticos) { diagnostico ->
                val pacienteNombre = pacientes.find { it.id == diagnostico.pacienteId }?.nombre ?: "Paciente no encontrado"
                val medicoNombre = medicos.find { it.id == diagnostico.medicoId }?.nombre ?: "Médico no encontrado"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Paciente: $pacienteNombre")
                        Text("Médico: $medicoNombre")
                        Text("Fecha: ${diagnostico.fecha}")
                        Text("Descripción: ${diagnostico.descripcion}")
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioDiagnosticoDialog(
            diagnostico = null,
            pacientes = pacientes,
            medicos = medicos,
            onDismiss = { showDialog = false },
            onGuardar = { nuevoDiagnostico ->
                diagnosticoViewModel.guardarDiagnostico(
                    nuevoDiagnostico,
                    onSuccess = { showDialog = false },
                    onError = { e -> Log.e("DiagnosisScreen", "Error al guardar diagnóstico: ${e.message}") }
                )
            }
        )
    }
}
