package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Paciente
import kotlinx.coroutines.tasks.await

class PacienteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("pacientes")

    suspend fun guardarPaciente(paciente: Paciente) {
        paciente.id?.let {
            collection.document(it).set(paciente).await()
        } ?: run {
            collection.add(paciente).await()
        }
    }

    suspend fun obtenerTodos(): List<Paciente> {
        return collection.get().await().toObjects(Paciente::class.java)
    }

    suspend fun obtenerPorId(id: String): Paciente? {
        return collection.document(id).get().await().toObject(Paciente::class.java)
    }
}
