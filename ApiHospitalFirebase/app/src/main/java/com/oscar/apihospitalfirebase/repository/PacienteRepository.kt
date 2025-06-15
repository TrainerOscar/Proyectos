package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Paciente
import kotlinx.coroutines.tasks.await

class PacienteRepository {
    private val db = FirebaseFirestore.getInstance()
    private val coleccion = db.collection("pacientes")

    suspend fun guardarPaciente(paciente: Paciente) {
        coleccion.document(paciente.id).set(paciente).await()
    }

    suspend fun obtenerTodos(): List<Paciente> {
        val snapshot = coleccion.get().await()
        return snapshot.toObjects(Paciente::class.java)
    }

    suspend fun obtenerPorId(id: String): Paciente? {
        val doc = coleccion.document(id).get().await()
        return doc.toObject(Paciente::class.java)
    }

    suspend fun actualizarPaciente(paciente: Paciente) {
        coleccion.document(paciente.id).set(paciente).await()
    }

    suspend fun eliminarPaciente(id: String) {
        coleccion.document(id).delete().await()
    }
}
