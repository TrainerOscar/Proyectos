package com.oscar.apihospitalfirebase.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Cita

@Composable
fun FormularioCitaDialog(
    cita: Cita? = null,
    onDismiss: () -> Unit,
    onGuardar: (Cita) -> Unit
) {
    var id by remember { mutableStateOf(cita?.id ?: "") }
    var pacienteId by remember { mutableStateOf(cita?.pacienteId ?: "") }
    var medicoId by remember { mutableStateOf(cita?.medicoId ?: "") }
    var fecha by remember { mutableStateOf(cita?.fecha ?: "") }
    var hora by remember { mutableStateOf(cita?.hora ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onGuardar(
                    Cita(
                        id = id,
                        pacienteId = pacienteId,
                        medicoId = medicoId,
                        fecha = fecha,
                        hora = hora
                    )
                )
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text(text = if (cita == null) "Nueva Cita" else "Editar Cita") },
        text = {
            Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                OutlinedTextField(
                    value = id,
                    onValueChange = { id = it },
                    label = { Text("ID") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = pacienteId,
                    onValueChange = { pacienteId = it },
                    label = { Text("ID del Paciente") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = medicoId,
                    onValueChange = { medicoId = it },
                    label = { Text("ID del Médico") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    label = { Text("Hora") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
