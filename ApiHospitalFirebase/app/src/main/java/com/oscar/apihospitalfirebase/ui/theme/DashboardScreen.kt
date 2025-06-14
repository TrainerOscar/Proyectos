package com.oscar.apihospitalfirebase.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                    icon = { Icon(Icons.Default.Message, contentDescription = null) },
                    label = { Text("Mensajes") },
                    selected = false,
                    onClick = { /* TODO */ }
                )
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.Help, contentDescription = null) },
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
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) },
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color(0xFFF2F2F2))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text("Medicinas", fontSize = 20.sp)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(vertical = 8.dp)
                                    .background(Color(0xFF2196F3), RoundedCornerShape(10.dp))
                                    .clickable { /* Navegar a Medicinas */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Ver tus medicinas", color = Color.White, fontSize = 16.sp)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Historial", fontSize = 20.sp)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(vertical = 8.dp)
                                    .background(Color(0xFF4CAF50), RoundedCornerShape(10.dp))
                                    .clickable { /* Navegar a Historial */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Tu historial clínico", color = Color.White, fontSize = 16.sp)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Calendario", fontSize = 20.sp)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(vertical = 8.dp)
                                    .background(Color(0xFFFF9800), RoundedCornerShape(10.dp))
                                    .clickable { /* Navegar a Calendario */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Ver citas", color = Color.White, fontSize = 16.sp)
                            }
                        }
                    }
                }
            )
        }
    )
}
