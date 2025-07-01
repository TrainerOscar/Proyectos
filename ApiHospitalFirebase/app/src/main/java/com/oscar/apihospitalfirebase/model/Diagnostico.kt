package com.oscar.apihospitalfirebase.model

data class Diagnostico(
    var id: String? = null,
    var descripcion: String? = null,
    var recomendaciones: String? = null,
    var fecha: String? = null,
    var pacienteId: String? = null,
    var medicoId: String? = null
)
