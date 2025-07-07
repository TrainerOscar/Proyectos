package com.oscar.apihospitalfirebase.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.ui.components.DropdownMenuBox

@Composable
fun FormularioDiagnosticoDialog(
    diagnostico: Diagnostico?,
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    onDismiss: () -> Unit,
    onGuardar: (Diagnostico) -> Unit
) {
    var descripcion by remember { mutableStateOf(diagnostico?.descripcion ?: "") }
    var recomendaciones by remember { mutableStateOf(diagnostico?.recomendaciones ?: "") }
    var fecha by remember { mutableStateOf(diagnostico?.fecha ?: "") }
    var pacienteSeleccionado by remember { mutableStateOf(diagnostico?.pacienteId ?: "") }
    var medicoSeleccionado by remember { mutableStateOf(diagnostico?.medicoId ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val nuevoDiagnostico = Diagnostico(
                    id = diagnostico?.id ?: System.currentTimeMillis().toString(),
                    descripcion = descripcion,
                    recomendaciones = recomendaciones,
                    fecha = fecha,
                    pacienteId = pacienteSeleccionado,
                    medicoId = medicoSeleccionado
                )
                onGuardar(nuevoDiagnostico)
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        },
        title = { Text("Formulario de Diagnóstico") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Descripción") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = recomendaciones,
                    onValueChange = { recomendaciones = it },
                    label = { Text("Recomendaciones") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") },
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
