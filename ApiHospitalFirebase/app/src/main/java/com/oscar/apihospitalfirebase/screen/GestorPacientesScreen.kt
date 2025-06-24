package com.oscar.apihospitalfirebase.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oscar.apihospitalfirebase.model.Paciente

@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun FormularioPacienteDialog(
    paciente: Paciente?,
    onDismiss: () -> Unit,
    onGuardar: (Paciente) -> Unit
) {
    var nombre by remember { mutableStateOf(paciente?.nombre ?: "") }
    var direccion by remember { mutableStateOf(paciente?.direccion ?: "") }
    var direccion by remember { mutableStateOf(paciente?.direccion ?: "") }
    var edad by remember { mutableStateOf(paciente?.edad?.toString() ?: "") }
    var genero by remember { mutableStateOf(paciente?.genero ?: "") }

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
                    onValueChange = {
                        edad = it.filter { ch -> ch.isDigit() }
                    },
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
                    label = { Text("Direccion") },
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
                        telefono =
                        motivo =
                        fechaCita =
                        horaCita =
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



@Composable

fun PacienteItem(
    paciente: Paciente,
    onEditar: (Paciente) -> Unit,
    onEliminar: (Paciente) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(paciente.nombre)
            Row {
                TextButton(onClick = { onEditar(paciente) }) {
                    Text("Editar")
                }
                TextButton(onClick = { onEliminar(paciente) }) {
                    Text("Eliminar")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestorPacientesScreen(
    pacientes: List<Paciente>,
    onGuardarPaciente: (Paciente) -> Unit,
    onEliminarPaciente: (Paciente) -> Unit
) {
    var pacienteDialogAbierto by remember { mutableStateOf(false) }
    var pacienteEditando by remember { mutableStateOf<Paciente?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestor de Pacientes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                pacienteEditando = null
                pacienteDialogAbierto = true
            }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(pacientes) { paciente ->
                PacienteItem(
                    paciente = paciente,
                    onEditar = {
                        pacienteEditando = it
                        pacienteDialogAbierto = true
                    },
                    onEliminar = { onEliminarPaciente(it) }
                )
            }
        }
    }

    if (pacienteDialogAbierto) {
        FormularioPacienteDialog(
            paciente = pacienteEditando,
            onDismiss = { pacienteDialogAbierto = false },
            onGuardar = {
                onGuardarPaciente(it)
                pacienteDialogAbierto = false
            }
        )
    }
}
