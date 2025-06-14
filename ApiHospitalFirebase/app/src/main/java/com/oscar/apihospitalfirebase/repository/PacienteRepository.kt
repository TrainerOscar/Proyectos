package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oscar.apihospitalfirebase.model.Paciente
import kotlinx.coroutines.tasks.await

class PacienteRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("pacientes")

    suspend fun obtenerTodos(): List<Paciente> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Paciente::class.java)
    }

    suspend fun obtenerPorId(id: String): Paciente? {
        val doc = collection.document(id).get().await()
        return doc.toObject(Paciente::class.java)
    }

    suspend fun guardar(paciente: Paciente) {
        // Si el ID está vacío, Firebase genera uno nuevo
        val id = if (paciente.id.isEmpty()) collection.document().id else paciente.id
        val pacienteConId = paciente.copy(id = id)
        collection.document(id).set(pacienteConId).await()
    }
}
