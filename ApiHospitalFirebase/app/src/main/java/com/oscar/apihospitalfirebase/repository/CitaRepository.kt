package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oscar.apihospitalfirebase.model.Cita
import kotlinx.coroutines.tasks.await

class CitaRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("citas")

    suspend fun obtenerTodos(): List<Cita> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Cita::class.java)
    }

    suspend fun obtenerPorId(id: String): Cita? {
        val doc = collection.document(id).get().await()
        return doc.toObject(Cita::class.java)
    }

    suspend fun guardar(cita: Cita) {
        val id = if (cita.id.isEmpty()) collection.document().id else cita.id
        val citaConId = cita.copy(id = id)
        collection.document(id).set(citaConId).await()
    }
}
