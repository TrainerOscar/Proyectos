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
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.ui.dialog.FormularioMedicoDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorScreen(viewModel: MedicoViewModel = viewModel()) {
    var showDialog by remember { mutableStateOf(false) }
    var medicos by remember { mutableStateOf<List<Medico>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewModel.obtenerMedicos { medicos = it }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Médicos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Médico")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(medicos) { medico ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Nombre: ${medico.nombre}")
                        Text(text = "Especialidad: ${medico.especialidad}")
                        Text(text = "Teléfono: ${medico.telefono}")
                        Text(text = "Correo: ${medico.correo}")
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
                    onError = { e -> Log.e("DoctorScreen", "Error al guardar médico: ${e.message}") }
                )
            }
        )
    }
}
