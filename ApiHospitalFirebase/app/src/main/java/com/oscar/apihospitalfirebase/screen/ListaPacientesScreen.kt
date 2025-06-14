package com.oscar.apihospitalfirebase.screen



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.viewmodel.PacienteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaPacientesScreen(pacienteViewModel: PacienteViewModel = viewModel()) {
    var pacientes by remember { mutableStateOf<List<Paciente>>(emptyList()) }

    LaunchedEffect(true) {
        pacienteViewModel.obtenerPacientes { lista ->
            pacientes = lista
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pacientes Registrados") }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
            items(pacientes) { paciente ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nombre: ${paciente.nombre}", fontSize = 16.sp)
                        Text("Edad: ${paciente.edad}", fontSize = 14.sp)
                        Text("Género: ${paciente.genero}", fontSize = 14.sp)
                        Text("Teléfono: ${paciente.telefono}", fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
