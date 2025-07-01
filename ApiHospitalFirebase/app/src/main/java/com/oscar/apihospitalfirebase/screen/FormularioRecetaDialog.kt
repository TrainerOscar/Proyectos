package com.oscar.apihospitalfirebase.ui.dialog

import Receta
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente


@Composable
fun FormularioRecetaDialog(
    receta: Receta?,
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    onDismiss: () -> Unit,
    onGuardar: (Receta) -> Unit
) {
    var medicamento by remember { mutableStateOf(receta?.medicamento ?: "") }
    var dosis by remember { mutableStateOf(receta?.dosis ?: "") }
    var indicaciones by remember { mutableStateOf(receta?.indicaciones ?: "") }
    var pacienteSeleccionado by remember { mutableStateOf(receta?.pacienteId ?: "") }
    var medicoSeleccionado by remember { mutableStateOf(receta?.medicoId ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val nuevaReceta = Receta(
                    id = receta?.id ?: System.currentTimeMillis().toString(),
                    medicamento = medicamento,
                    dosis = dosis,
                    indicaciones = indicaciones,
                    pacienteId = pacienteSeleccionado,
                    medicoId = medicoSeleccionado
                )
                onGuardar(nuevaReceta)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Formulario de Receta") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = medicamento,
                    onValueChange = { medicamento = it },
                    label = { Text("Medicamento") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = dosis,
                    onValueChange = { dosis = it },
                    label = { Text("Dosis") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = indicaciones,
                    onValueChange = { indicaciones = it },
                    label = { Text("Indicaciones") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text("Selecciona un Paciente:")
                DropdownMenuBox(
                    opciones = pacientes.mapNotNull { it.nombre?.let { n -> n to (it.id ?: "") } },
                    seleccionado = pacienteSeleccionado,
                    onSeleccionar = { pacienteSeleccionado = it }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text("Selecciona un Médico:")
                DropdownMenuBox(
                    opciones = medicos.mapNotNull { it.nombre?.let { n -> n to (it.id ?: "") } },
                    seleccionado = medicoSeleccionado,
                    onSeleccionar = { medicoSeleccionado = it }
                )
            }
        }
    )
}
