package com.oscar.apihospitalfirebase.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.oscar.apihospitalfirebase.model.Cita
import kotlinx.coroutines.tasks.await

class CitaRepository {
    private val db = FirebaseDatabase.getInstance()
    private val citasRef = db.getReference("citas")

    // Guardar una nueva cita
    suspend fun guardarCita(cita: Cita) {
        if (cita.id.isEmpty()) {
            val nuevaRef = citasRef.push()
            cita.id = nuevaRef.key ?: ""
            nuevaRef.setValue(cita).await()
        } else {
            citasRef.child(cita.id).setValue(cita).await()
        }
    }

    // Obtener todas las citas
    suspend fun obtenerTodas(): List<Cita> {
        val snapshot = citasRef.get().await()
        val lista = mutableListOf<Cita>()
        for (child in snapshot.children) {
            val cita = child.getValue(Cita::class.java)
            cita?.let { lista.add(it) }
        }
        return lista
    }

    // Obtener una cita por ID
    suspend fun obtenerPorId(id: String): Cita? {
        val snapshot = citasRef.child(id).get().await()
        return snapshot.getValue(Cita::class.java)
    }

    // Actualizar una cita
    suspend fun actualizarCita(cita: Cita) {
        citasRef.child(cita.id).setValue(cita).await()
    }

    // Eliminar una cita
    suspend fun eliminarCita(id: String) {
        citasRef.child(id).removeValue().await()
    }
}
