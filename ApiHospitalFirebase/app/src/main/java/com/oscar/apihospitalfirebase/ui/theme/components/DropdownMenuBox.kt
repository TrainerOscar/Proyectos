package com.oscar.apihospitalfirebase.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun DropdownMenuBox(
    opciones: List<Pair<String, String>>,
    seleccionado: String,
    onSeleccionar: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val seleccionActual = opciones.find { it.second == seleccionado }?.first ?: "Seleccionar"

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(
            onClick = { expanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(seleccionActual)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
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
