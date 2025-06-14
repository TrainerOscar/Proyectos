package com.oscar.apihospitalfirebase.model

data class Cita(
    val id: String = "",
    val pacienteId: String = "",
    val medicoId: String = "",
    val fecha: String = "", // puedes usar Timestamp si prefieres
    val motivo: String = ""
)
