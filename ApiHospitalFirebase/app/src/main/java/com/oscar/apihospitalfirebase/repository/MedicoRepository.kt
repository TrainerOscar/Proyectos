package com.oscar.apihospitalfirebase.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.oscar.apihospitalfirebase.model.Medico
import kotlinx.coroutines.tasks.await

class MedicoRepository {
    private val db = FirebaseDatabase.getInstance()
    private val medicosRef = db.getReference("medicos")

    // Guardar un nuevo médico
    suspend fun guardarMedico(medico: Medico) {
        if (medico.id == null) {
            val nuevaRef = medicosRef.push()
            medico.id = nuevaRef.key.toString()
            nuevaRef.setValue(medico).await()
        } else {
            medicosRef.child(medico.id!!).setValue(medico).await()
        }
    }

    // Obtener todos los médicos
    suspend fun obtenerTodos(): List<Medico> {
        val snapshot = medicosRef.get().await()
        val lista = mutableListOf<Medico>()
        for (child in snapshot.children) {
            val medico = child.getValue(Medico::class.java)
            medico?.let { lista.add(it) }
        }
        return lista
    }

    // Obtener un médico por ID
    suspend fun obtenerPorId(id: String): Medico? {
        val snapshot = medicosRef.child(id).get().await()
        return snapshot.getValue(Medico::class.java)
    }

    // Actualizar un médico
    suspend fun actualizarMedico(medico: Medico) {
        medico.id?.let {
            medicosRef.child(it).setValue(medico).await()
        }
    }

    // Eliminar un médico
    suspend fun eliminarMedico(id: String) {
        medicosRef.child(id).removeValue().await()
    }
}
