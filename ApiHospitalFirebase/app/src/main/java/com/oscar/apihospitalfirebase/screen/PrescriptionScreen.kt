package com.oscar.apihospitalfirebase.screen

import com.oscar.apihospitalfirebase.model.Receta
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
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.ui.dialog.FormularioRecetaDialog
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionScreen(
    recetaViewModel: RecetaViewModel = viewModel(),
    pacientesExternos: List<Paciente>,
    medicosExternos: List<Medico>
) {
    var showDialog by remember { mutableStateOf(false) }
    var recetas by remember { mutableStateOf<List<Receta>>(emptyList()) }

    // Usamos directamente los datos externos, ya no hace falta cargarlos con ViewModel
    val pacientes = pacientesExternos
    val medicos = medicosExternos

    LaunchedEffect(Unit) {
        recetaViewModel.obtenerRecetas { recetas = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Recetas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Receta")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(recetas) { receta ->
                val pacienteNombre = pacientes.find { it.id == receta.pacienteId }?.nombre ?: "Paciente no encontrado"
                val medicoNombre = medicos.find { it.id == receta.medicoId }?.nombre ?: "Médico no encontrado"

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Paciente: $pacienteNombre")
                        Text("Médico: $medicoNombre")
                        Text("Medicamento: ${receta.medicamento}")
                        Text("Dosis: ${receta.dosis}")
                        Text("Indicaciones: ${receta.indicaciones}")
                    }
                }
            }
        }
    }

    if (showDialog) {
        FormularioRecetaDialog(
            receta = null,
            pacientes = pacientes,
            medicos = medicos,
            onDismiss = { showDialog = false },
            onGuardar = { nuevaReceta ->
                recetaViewModel.guardarReceta(
                    nuevaReceta,
                    onSuccess = { showDialog = false },
                    onError = { e -> Log.e("PrescriptionScreen", "Error al guardar receta: ${e.message}") }
                )
            }
        )
    }
}
