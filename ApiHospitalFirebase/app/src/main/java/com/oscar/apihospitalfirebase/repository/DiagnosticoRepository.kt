package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Diagnostico
import kotlinx.coroutines.tasks.await

class DiagnosticoRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("diagnosticos")

    suspend fun guardarDiagnostico(diagnostico: Diagnostico) {
        diagnostico.id?.let {
            collection.document(it).set(diagnostico).await()
        } ?: run {
            collection.add(diagnostico).await()
        }
    }

    suspend fun obtenerTodos(): List<Diagnostico> {
        return collection.get().await().toObjects(Diagnostico::class.java)
    }

    suspend fun obtenerPorId(id: String): Diagnostico? {
        return collection.document(id).get().await().toObject(Diagnostico::class.java)
    }
}
