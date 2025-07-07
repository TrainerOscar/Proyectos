package com.oscar.apihospitalfirebase.model

data class Cita(
    var id: String = "",
    val pacienteId: String = "",
    val medicoId: String = "",
    val fecha: String = "",
    val hora: String = "",
    var estado: String = "En espera" // Nuevo campo
)
