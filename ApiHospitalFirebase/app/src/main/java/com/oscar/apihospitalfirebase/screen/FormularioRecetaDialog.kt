package com.oscar.apihospitalfirebase.ui.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.model.Receta

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioRecetaDialog(
    receta: Receta?, // null para nueva receta
    pacientes: List<Paciente>,
    medicos: List<Medico>,
    onDismiss: () -> Unit,
    onGuardar: (Receta) -> Unit
) {
    var diagnosticoId by remember { mutableStateOf(receta?.diagnosticoId ?: "") }
    var medicamento by remember { mutableStateOf(receta?.medicamento ?: "") }
    var dosis by remember { mutableStateOf(receta?.dosis ?: "") }
    var frecuencia by remember { mutableStateOf(receta?.frecuencia ?: "") }
    var duracion by remember { mutableStateOf(receta?.duracion ?: "") }

    var pacienteSeleccionado by remember { mutableStateOf(receta?.pacienteId ?: "") }
    var medicoSeleccionado by remember { mutableStateOf(receta?.medicoId ?: "") }

    var expandedPaciente by remember { mutableStateOf(false) }
    var expandedMedico by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Formulario de Receta") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = diagnosticoId,
                    onValueChange = { diagnosticoId = it },
                    label = { Text("ID del Diagnóstico") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

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
                    value = frecuencia,
                    onValueChange = { frecuencia = it },
                    label = { Text("Frecuencia") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                OutlinedTextField(
                    value = duracion,
                    onValueChange = { duracion = it },
                    label = { Text("Duración") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                )

                // Dropdown para Paciente
                ExposedDropdownMenuBox(
                    expanded = expandedPaciente,
                    onExpandedChange = { expandedPaciente = !expandedPaciente }
                ) {
                    OutlinedTextField(
                        value = pacientes.find { it.id == pacienteSeleccionado }?.nombre ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Paciente") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedPaciente,
                        onDismissRequest = { expandedPaciente = false }
                    ) {
                        pacientes.forEach { paciente ->
                            DropdownMenuItem(
                                text = { Text(paciente.nombre) },
                                onClick = {
                                    pacienteSeleccionado = paciente.id
                                    expandedPaciente = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown para Médico
                ExposedDropdownMenuBox(
                    expanded = expandedMedico,
                    onExpandedChange = { expandedMedico = !expandedMedico }
                ) {
                    OutlinedTextField(
                        value = medicos.find { it.id == medicoSeleccionado }?.nombre ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Médico") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedMedico,
                        onDismissRequest = { expandedMedico = false }
                    ) {
                        medicos.forEach { medico ->
                            DropdownMenuItem(
                                text = { Text(medico.nombre) },
                                onClick = {
                                    medicoSeleccionado = medico.id
                                    expandedMedico = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val nuevaReceta = Receta(
                    id = receta?.id ?: System.currentTimeMillis().toString(),
                    diagnosticoId = diagnosticoId,
                    medicamento = medicamento,
                    dosis = dosis,
                    frecuencia = frecuencia,
                    duracion = duracion,
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
        }
    )
}
