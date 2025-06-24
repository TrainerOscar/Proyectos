package com.oscar.apihospitalfirebase.model

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.model.Medico
import kotlinx.coroutines.launch

@Composable
fun FormularioCitaDialog(
    cita: Cita?,
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    onDismiss: () -> Unit,
    onGuardar: (Cita) -> Unit
) {
    val scope = rememberCoroutineScope()
    var fecha by remember { mutableStateOf(cita?.fecha ?: "") }
    var hora by remember { mutableStateOf(cita?.hora ?: "") }
    var pacienteSeleccionado by remember { mutableStateOf(cita?.pacienteId ?: "") }
    var medicoSeleccionado by remember { mutableStateOf(cita?.medicoId ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val nuevaCita = Cita(
                    id = cita?.id ?: System.currentTimeMillis().toString(),
                    fecha = fecha,
                    hora = hora,
                    pacienteId = pacienteSeleccionado,
                    medicoId = medicoSeleccionado
                )
                onGuardar(nuevaCita)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Formulario de Cita") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    label = { Text("Hora") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Selecciona un Paciente:")
                DropdownMenuBox(
                    opciones = pacientes.map { it.nombre to it.id },
                    seleccionado = pacienteSeleccionado,
                    onSeleccionar = { pacienteSeleccionado = it }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Selecciona un Médico:")
                DropdownMenuBox(
                    opciones = medicos.map { it.nombre to it.id },
                    seleccionado = medicoSeleccionado,
                    onSeleccionar = { medicoSeleccionado = it }
                )
            }
        }
    )
}

@Composable
fun DropdownMenuBox(
    opciones: List<Pair<String, String>>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val seleccionActual = opciones.find { it.second == seleccionado }?.first ?: "Seleccionar"

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(seleccionActual)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            opciones.forEach { (nombre, id) ->
                DropdownMenuItem(
                    text = { Text(nombre) },
                    onClick = {
                        onSeleccionar(id)
                        expanded = false
                    }
                )
            }
        }
    }
}
