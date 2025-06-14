package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oscar.apihospitalfirebase.model.Diagnostico
import kotlinx.coroutines.tasks.await

class DiagnosticoRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("diagnosticos")

    suspend fun obtenerTodos(): List<Diagnostico> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Diagnostico::class.java)
    }

    suspend fun obtenerPorId(id: String): Diagnostico? {
        val doc = collection.document(id).get().await()
        return doc.toObject(Diagnostico::class.java)
    }

    suspend fun guardar(diagnostico: Diagnostico) {
        val id = if (diagnostico.id.isEmpty()) collection.document().id else diagnostico.id
        val diagnosticoConId = diagnostico.copy(id = id)
        collection.document(id).set(diagnosticoConId).await()
    }
}
