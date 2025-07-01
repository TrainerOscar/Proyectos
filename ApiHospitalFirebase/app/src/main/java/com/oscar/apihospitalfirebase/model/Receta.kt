package com.oscar.apihospitalfirebase.model

data class Receta(
    var id: String = "",
    var diagnosticoId: String = "",      // ID del diagnóstico relacionado
    var medicamento: String = "",        // Nombre del medicamento
    var dosis: String = "",              // Cantidad o concentración
    var frecuencia: String = "",         // Frecuencia de administración
    var duracion: String = "",           // Duración del tratamiento
    var indicaciones: String? = null,    // Indicaciones adicionales
    var pacienteId: String? = null,      // Si deseas vincular al paciente
    var medicoId: String? = null         // Si deseas vincular al médico
)
