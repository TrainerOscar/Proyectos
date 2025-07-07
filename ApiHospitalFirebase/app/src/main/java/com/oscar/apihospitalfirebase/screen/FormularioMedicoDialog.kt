package com.oscar.apihospitalfirebase.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Medico

@Composable
fun FormularioMedicoDialog(
    medico: Medico?, // null para nuevo
    onDismiss: () -> Unit,
    onGuardar: (Medico) -> Unit
) {
    var nombre by remember { mutableStateOf(medico?.nombre ?: "") }
    var especialidad by remember { mutableStateOf(medico?.especialidad ?: "") }
    var horario by remember { mutableStateOf(medico?.horario ?: "") }
    var sala by remember { mutableStateOf(medico?.sala ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val nuevoMedico = Medico(
                    id = medico?.id ?: System.currentTimeMillis().toString(),
                    nombre = nombre,
                    especialidad = especialidad,
                    horario = horario,
                    sala = sala
                )
                onGuardar(nuevoMedico)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Formulario de Médico") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = especialidad,
                    onValueChange = { especialidad = it },
                    label = { Text("Especialidad") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = horario,
                    onValueChange = { horario = it },
                    label = { Text("Horario") },
                    placeholder = { Text("Ej. 08:00 - 14:00") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = sala,
                    onValueChange = { sala = it },
                    label = { Text("Sala") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
    )
}
