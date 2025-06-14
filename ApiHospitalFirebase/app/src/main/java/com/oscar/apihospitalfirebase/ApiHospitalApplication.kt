package com.oscar.apihospitalfirebase
// ApiHospitalApplication.kt

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.FileInputStream

@SpringBootApplication
class ApiHospitalApplication

fun main(args: Array<String>) {
    val serviceAccount = FileInputStream("ruta-a-tu-archivo-serviceAccountKey.json")
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://TU_PROYECTO.firebaseio.com")
        .build()

    FirebaseApp.initializeApp(options)
    runApplication<ApiHospitalApplication>(*args)
}
