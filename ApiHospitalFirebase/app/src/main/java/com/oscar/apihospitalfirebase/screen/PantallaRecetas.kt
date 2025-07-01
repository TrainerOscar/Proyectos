package com.oscar.apihospitalfirebase.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Receta
import com.oscar.apihospitalfirebase.viewmodel.RecetaViewModel
import com.oscar.apihospitalfirebase.ui.dialog.FormularioRecetaDialog
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.model.Medico
@Composable
fun PantallaReceta(
    recetaViewModel: RecetaViewModel,
    pacientes: List<Paciente>,
    medicos: List<Medico>
) {
    var showDialog by remember { mutableStateOf(false) }
    var recetas by remember { mutableStateOf(listOf<Receta>()) }

    // Cargar recetas al inicio
    LaunchedEffect(Unit) {
        recetaViewModel.obtenerRecetas { recetas = it }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { showDialog = true }) {
            Text("Agregar Receta")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(recetas) { receta ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Diagnóstico ID: ${receta.diagnosticoId}")
                    Text("Medicamento: ${receta.medicamento}")
                    Text("Dosis: ${receta.dosis}")
                    Text("Frecuencia: ${receta.frecuencia}")
                    Text("Duración: ${receta.duracion}")
                }
            }
        }

        // Mostrar diálogo
        if (showDialog) {
            FormularioRecetaDialog(
                receta = null,
                pacientes = pacientes,
                medicos = medicos,
                onDismiss = { showDialog = false },
                onGuardar = { nuevaReceta ->
                    recetaViewModel.guardarReceta(
                        receta = nuevaReceta,
                        onSuccess = {
                            showDialog = false
                            recetaViewModel.obtenerRecetas { recetas = it }
                        },
                        onError = { e ->
                            Log.e("PantallaReceta", "Error al guardar receta: ${e.message}")
                        }
                    )
                }
            )
        }
    }
}
