package com.oscar.apihospitalfirebase.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Paciente

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestorPacientesScreen(
    paciente: Paciente? = null,
    onDismiss: () -> Unit = {},
    onGuardar: (Paciente) -> Unit = {}
) {
    var nombre by remember { mutableStateOf(paciente?.nombre ?: "") }
    var direccion by remember { mutableStateOf(paciente?.direccion ?: "") }
    var edad by remember { mutableStateOf(paciente?.edad?.toString() ?: "") }
    var genero by remember { mutableStateOf(paciente?.genero ?: "") }
    var telefono by remember { mutableStateOf(paciente?.telefono ?: "") }
    var motivo by remember { mutableStateOf(paciente?.motivo ?: "") }
    var fechaCita by remember { mutableStateOf(paciente?.fechaCita ?: "") }
    var horaCita by remember { mutableStateOf(paciente?.horaCita ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (paciente == null) "Nuevo Paciente" else "Editar Paciente") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it.filter { ch -> ch.isDigit() } },
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = motivo,
                    onValueChange = { motivo = it },
                    label = { Text("Motivo") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = fechaCita,
                    onValueChange = { fechaCita = it },
                    label = { Text("Fecha Cita") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = horaCita,
                    onValueChange = { horaCita = it },
                    label = { Text("Hora Cita") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (nombre.isNotBlank() && edad.isNotBlank()) {
                    val nuevoPaciente = Paciente(
                        id = paciente?.id ?: System.currentTimeMillis().toString(),
                        nombre = nombre,
                        edad = edad.toInt(),
                        genero = genero,
                        direccion = direccion,
                        telefono = telefono,
                        motivo = motivo,
                        fechaCita = fechaCita,
                        horaCita = horaCita
                    )
                    onGuardar(nuevoPaciente)
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}