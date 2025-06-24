package com.example.registroestudiante

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.registroestudiante.ui.theme.RegistroEstudianteTheme

class MainActivity : ComponentActivity() {



@Composable
fun DatosEstudiantes(modifer: Modifer = Modifer) {
    var nombre by remember { mutableStateMapOf("")}
    var Apellido by remember { mutableStateMapOf("")}
    var Carrera by remember { mutableStateMapOf("")}
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Registro de estudiante") }
            )
        },
        content = { padding ->
            Column (
                modifer = modifer.fillMaxSize()
                    .padding(padding)
            )   }

                OutlinedTextField(
                    value = nombre,
                    onValueCharge = { nombre = it},
                    label = {Text( "Nombre")},
                    modifer = Modifer,padding

                )


         }

}