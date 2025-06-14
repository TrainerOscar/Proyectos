package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oscar.apihospitalfirebase.model.Medico
import kotlinx.coroutines.tasks.await

class MedicoRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("medicos")

    suspend fun obtenerTodos(): List<Medico> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Medico::class.java)
    }

    suspend fun obtenerPorId(id: String): Medico? {
        val doc = collection.document(id).get().await()
        return doc.toObject(Medico::class.java)
    }

    suspend fun guardar(medico: Medico) {
        val id = if (medico.id.isEmpty()) collection.document().id else medico.id
        val medicoConId = medico.copy(id = id)
        collection.document(id).set(medicoConId).await()
    }
}
