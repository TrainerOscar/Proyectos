package com.oscar.apihospitalfirebase.screen



import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Paciente

@Composable
fun FormularioPacienteDialog(
    paciente: Paciente?,
    onDismiss: () -> Unit,
    onSave: (Paciente) -> Unit
) {
    val context = LocalContext.current

    var nombre by remember { mutableStateOf(TextFieldValue(paciente?.nombre ?: "")) }
    var edad by remember { mutableStateOf(TextFieldValue(paciente?.edad?.toString() ?: "")) }
    var motivo by remember { mutableStateOf(TextFieldValue(paciente?.motivo ?: "")) }
    var fechaCita by remember { mutableStateOf(TextFieldValue(paciente?.fechaCita ?: "")) }
    var horaCita by remember { mutableStateOf(TextFieldValue(paciente?.horaCita ?: "")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = if (paciente == null) "Nuevo Paciente" else "Editar Paciente")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = motivo,
                    onValueChange = { motivo = it },
                    label = { Text("Motivo") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fechaCita,
                    onValueChange = { fechaCita = it },
                    label = { Text("Fecha de Cita") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = horaCita,
                    onValueChange = { horaCita = it },
                    label = { Text("Hora de Cita") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (nombre.text.isNotBlank() && edad.text.isNotBlank()) {
                        val pacienteActualizado = Paciente(
                            id = paciente?.id ?: "",
                            nombre = nombre.text,
                            edad = edad.text.toIntOrNull() ?: 0,
                            motivo = motivo.text,
                            fechaCita = fechaCita.text,
                            horaCita = horaCita.text




                        )
                        onSave(pacienteActualizado)
                    } else {
                        Toast.makeText(context, "Por favor, completa los campos requeridos.", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
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
