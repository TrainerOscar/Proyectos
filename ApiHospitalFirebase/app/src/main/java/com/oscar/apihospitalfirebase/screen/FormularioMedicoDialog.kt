package com.oscar.apihospitalfirebase.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Medico

@Composable
fun FormularioMedicoDialog(
    medico: Medico?,
    onDismiss: () -> Unit,
    onGuardar: (Medico) -> Unit
) {
    var nombre by remember { mutableStateOf(medico?.nombre ?: "") }
    var especialidad by remember { mutableStateOf(medico?.especialidad ?: "") }
    var telefono by remember { mutableStateOf(medico?.telefono ?: "") }
    var correo by remember { mutableStateOf(medico?.correo ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val nuevoMedico = Medico(
                    id = medico?.id ?: System.currentTimeMillis().toString(),
                    nombre = nombre,
                    especialidad = especialidad,
                    telefono = telefono,
                    correo = correo
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
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        }
    )
}
