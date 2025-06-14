package com.oscar.apihospitalfirebase.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oscar.apihospitalfirebase.viewmodel.MedicoViewModel
import com.oscar.apihospitalfirebase.model.Medico
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(medicoViewModel: MedicoViewModel = viewModel()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val medicos = remember { mutableStateListOf<Medico>() }

    LaunchedEffect(Unit) {
        medicoViewModel.obtenerMedicos { lista ->
            medicos.clear()
            medicos.addAll(lista)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(260.dp)) {
                Text(
                    text = "Hospital",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 22.sp
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.Message, contentDescription = null) },
                    label = { Text("Mensajes") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.Help, contentDescription = null) },
                    label = { Text("Ayuda") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    label = { Text("Opciones") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    label = { Text("Contraseña") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null) },
                    label = { Text("Sign Out") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
            }
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Dashboard") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                    ) {
                        Text("Médicos registrados", fontSize = 20.sp)

                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(medicos) { medico ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text("Nombre: ${medico.nombre}", fontSize = 16.sp)
                                        Text("Especialidad: ${medico.especialidad}", fontSize = 14.sp)
                                        Text("Teléfono: ${medico.telefono}", fontSize = 14.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            )
        }
    )
}
