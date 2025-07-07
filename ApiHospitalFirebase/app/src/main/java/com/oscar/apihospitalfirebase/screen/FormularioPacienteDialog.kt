package com.oscar.apihospitalfirebase.ui.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Paciente
import java.util.*

@Composable
fun FormularioPacienteDialog(
    paciente: Paciente?, // null para nuevo
    onDismiss: () -> Unit,
    onGuardar: (Paciente) -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    var nombre by remember { mutableStateOf(paciente?.nombre ?: "") }
    var edadTexto by remember { mutableStateOf(paciente?.edad?.toString() ?: "") }
    var genero by remember { mutableStateOf(paciente?.genero ?: "") }
    var direccion by remember { mutableStateOf(paciente?.direccion ?: "") }
    var telefono by remember { mutableStateOf(paciente?.telefono ?: "") }
    var motivo by remember { mutableStateOf(paciente?.motivo ?: "") }
    var fechaCita by remember { mutableStateOf(paciente?.fechaCita ?: "") }
    var horaCita by remember { mutableStateOf(paciente?.horaCita ?: "") }

    val showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val edadInt = edadTexto.toIntOrNull() ?: 0
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
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )
                OutlinedTextField(
                    value = edadTexto,
                    onValueChange = { edadTexto = it },
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
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
                    label = { Text("Motivo de consulta") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                // Campo de fecha
                OutlinedTextField(
                    value = fechaCita,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Fecha de cita") },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker.value = true }) {
                            Icon(Icons.Default.CalendarToday, contentDescription = "Seleccionar fecha")
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                // Campo de hora
                OutlinedTextField(
                    value = horaCita,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Hora de cita") },
                    trailingIcon = {
                        IconButton(onClick = { showTimePicker.value = true }) {
                            Icon(Icons.Default.AccessTime, contentDescription = "Seleccionar hora")
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                // DatePicker
                if (showDatePicker.value) {
                    DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            fechaCita = "%02d/%02d/%04d".format(day, month + 1, year)
                            showDatePicker.value = false
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }

                // TimePicker
                if (showTimePicker.value) {
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            horaCita = String.format("%02d:%02d", hour, minute)
                            showTimePicker.value = false
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                }
            }
        }
    )
}
