package com.oscar.apihospitalfirebase.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.oscar.apihospitalfirebase.model.Paciente
import kotlinx.coroutines.tasks.await

class PacienteRepository {
    private val db = FirebaseDatabase.getInstance()
    private val pacientesRef = db.getReference("pacientes")

    // Guardar un nuevo paciente
    suspend fun guardarPaciente(paciente: Paciente) {
        if (paciente.id.isEmpty()) {
            val nuevaRef = pacientesRef.push()
            paciente.id = nuevaRef.key ?: ""
            nuevaRef.setValue(paciente).await()
        } else {
            pacientesRef.child(paciente.id).setValue(paciente).await()
        }
    }

    // Obtener todos los pacientes
    suspend fun obtenerTodos(): List<Paciente> {
        val snapshot = pacientesRef.get().await()
        val lista = mutableListOf<Paciente>()
        for (child in snapshot.children) {
            val paciente = child.getValue(Paciente::class.java)
            paciente?.let { lista.add(it) }
        }
        return lista
    }

    // Obtener un paciente por ID
    suspend fun obtenerPorId(id: String): Paciente? {
        val snapshot = pacientesRef.child(id).get().await()
        return snapshot.getValue(Paciente::class.java)
    }

    // Actualizar un paciente
    suspend fun actualizarPaciente(paciente: Paciente) {
        pacientesRef.child(paciente.id).setValue(paciente).await()
    }

    // Eliminar un paciente
    suspend fun eliminarPaciente(id: String) {
        pacientesRef.child(id).removeValue().await()
    }
}
