package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Cita
import kotlinx.coroutines.tasks.await

class CitaRepository {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("citas")

    suspend fun guardarCita(cita: Cita) {
        coleccion.document(cita.id).set(cita).await()
    }

    suspend fun obtenerTodas(): List<Cita> {
        val snapshot = coleccion.get().await()
        return snapshot.toObjects(Cita::class.java)
    }

    suspend fun obtenerPorId(id: String): Cita? {
        val doc = coleccion.document(id).get().await()
        return doc.toObject(Cita::class.java)
    }

    suspend fun actualizarCita(cita: Cita) {
        coleccion.document(cita.id).set(cita).await()
    }

    suspend fun eliminarCita(id: String) {
        coleccion.document(id).delete().await()
    }
}