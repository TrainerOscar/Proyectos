package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Medico
import kotlinx.coroutines.tasks.await

class MedicoRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("medicos")

    suspend fun guardarMedico(medico: Medico) {
        medico.id?.let {
            collection.document(it).set(medico).await()
        } ?: run {
            collection.add(medico).await()
        }
    }

    suspend fun obtenerTodos(): List<Medico> {
        return collection.get().await().toObjects(Medico::class.java)
    }

    suspend fun obtenerPorId(id: String): Medico? {
        return collection.document(id).get().await().toObject(Medico::class.java)
    }
}
