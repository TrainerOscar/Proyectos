package com.oscar.apihospitalfirebase.model

data class Paciente(
    var id: String = "",
    val nombre: String = "",
    val edad: Int = 0,
    val genero: String = "",
    val direccion: String = "",
    val telefono: String = "",
    val motivo: String,
    val fechaCita: String,
    val horaCita: String
)
