package com.oscar.apihospitalfirebase.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Paciente

@Composable
fun FormularioPacienteDialog(
    paciente: Paciente?, // null para nuevo
    onDismiss: () -> Unit,
    onGuardar: (Paciente) -> Unit
) {
    var nombre by remember { mutableStateOf(paciente?.nombre ?: "") }
    var edadTexto by remember { mutableStateOf(paciente?.edad?.toString() ?: "") }
    var genero by remember { mutableStateOf(paciente?.genero ?: "") }
    var direccion by remember { mutableStateOf(paciente?.direccion ?: "") }
    var telefono by remember { mutableStateOf(paciente?.telefono ?: "") }
    var motivo by remember { mutableStateOf(paciente?.motivo ?: "") }
    var fechaCita by remember { mutableStateOf(paciente?.fechaCita ?: "") }
    var horaCita by remember { mutableStateOf(paciente?.horaCita ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val edadInt = edadTexto.toIntOrNull() ?: 0  // conversión segura
                val nuevoPaciente = Paciente(
                    id = paciente?.id ?: System.currentTimeMillis().toString(),
                    nombre = nombre,
                    edad = edadInt,
                    genero = genero,
                    direccion = direccion,
                    telefono = telefono,
                    motivo = motivo,
                    fechaCita = fechaCita,
                    horaCita = horaCita
                )
                onGuardar(nuevoPaciente)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Formulario de Paciente") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = edadTexto,
                    onValueChange = { edadTexto = it },
                    label = { Text("Edad") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = genero,
                    onValueChange = { genero = it },
                    label = { Text("Género") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text("Dirección") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = telefono,
                    onValueChange = { telefono = it },
                    label = { Text("Teléfono") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = motivo,
                    onValueChange = { motivo = it },
                    label = { Text("Motivo de consulta") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = fechaCita,
                    onValueChange = { fechaCita = it },
                    label = { Text("Fecha de cita") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = horaCita,
                    onValueChange = { horaCita = it },
                    label = { Text("Hora de cita") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        }
    )
}
