package com.oscar.apihospitalfirebase.model

data class Diagnostico(
    var id: String = "",
    var descripcion: String = "",
    var recomendaciones: String = "",
    var fecha: String = "",
    var pacienteId: String = "",
    var medicoId: String = ""
)
