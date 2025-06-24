package com.oscar.weatherapi



import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { WeatherApp() }
    }
}

@Composable
fun WeatherApp() {
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var temperature by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var icon by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        BasicTextField(value = city, onValueChange = { city = it }, modifier = Modifier.fillMaxWidth().padding(8.dp))
        Button(onClick = {
            scope.launch {
                try {
                    val resp = Repository.service.getWeatherByCity(city.text, BuildConfig.OPENWEATHER_API_KEY)
                    temperature = "${resp.main.temp} °C"
                    description = resp.weather[0].description
                    icon = "https://openweathermap.org/img/wn/${resp.weather[0].icon}@2x.png"
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al obtener clima", Toast.LENGTH_LONG).show()
                }
            }
        }) { Text("Obtener Clima") }

        Spacer(Modifier.height(16.dp))
        Text("Temperatura: $temperature")
        Text("Descripción: $description")
        if (icon.isNotEmpty()) Image(painter = rememberAsyncImagePainter(icon), contentDescription = null)
    }
}