package com.oscar.apihospitalfirebase.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.oscar.apihospitalfirebase.model.Cita
import kotlinx.coroutines.tasks.await

class CitaRepository {
    private val db = FirebaseDatabase.getInstance()
    private val citasRef = db.getReference("citas")

    suspend fun guardarCita(cita: Cita) {
        if (cita.estado.isBlank()) cita.estado = "En espera"
        if (cita.id.isEmpty()) {
            val nuevaRef = citasRef.push()
            cita.id = nuevaRef.key ?: ""
            nuevaRef.setValue(cita).await()
        } else {
            citasRef.child(cita.id).setValue(cita).await()
        }
    }

    suspend fun obtenerTodas(): List<Cita> {
        val snapshot = citasRef.get().await()
        val lista = mutableListOf<Cita>()
        for (child in snapshot.children) {
            val cita = child.getValue(Cita::class.java)?.apply {
                if (this.estado.isBlank()) this.estado = "En espera"
            }
            cita?.let { lista.add(it) }
        }
        return lista
    }

    suspend fun obtenerPorId(id: String): Cita? {
        val snapshot = citasRef.child(id).get().await()
        return snapshot.getValue(Cita::class.java)?.apply {
            if (estado.isBlank()) estado = "En espera"
        }
    }

    suspend fun actualizarCita(cita: Cita) {
        if (cita.estado.isBlank()) cita.estado = "En espera"
        citasRef.child(cita.id).setValue(cita).await()
    }

    suspend fun eliminarCita(id: String) {
        citasRef.child(id).removeValue().await()
    }
}
