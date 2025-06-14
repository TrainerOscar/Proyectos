package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Cita
import kotlinx.coroutines.tasks.await

class CitaRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("citas")

    suspend fun guardarCita(cita: Cita) {
        cita.id?.let {
            collection.document(it).set(cita).await()
        } ?: run {
            collection.add(cita).await()
        }
    }

    suspend fun obtenerTodas(): List<Cita> {
        return collection.get().await().toObjects(Cita::class.java)
    }

    suspend fun obtenerPorId(id: String): Cita? {
        return collection.document(id).get().await().toObject(Cita::class.java)
    }

    suspend fun obtenerCitasPorMedico(medicoId: String): List<Cita> {
        return collection.whereEqualTo("medicoId", medicoId).get().await().toObjects(Cita::class.java)
    }
}
