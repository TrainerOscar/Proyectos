package com.oscar.apihospitalfirebase.model

data class Receta(
    var id: String = "",
    val citaId: String = "",
    val medicamento: String = "",
    val dosis: String = "",
    val indicaciones: String = ""
)
