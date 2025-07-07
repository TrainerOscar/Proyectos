package com.oscar.apihospitalfirebase.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.oscar.apihospitalfirebase.model.Diagnostico
import kotlinx.coroutines.tasks.await

class DiagnosticoRepository {
    private val db = FirebaseDatabase.getInstance()
    private val diagnosticosRef = db.getReference("diagnosticos")

    // Guardar un nuevo diagnóstico
    suspend fun guardarDiagnostico(diagnostico: Diagnostico) {
        if (diagnostico.id.isEmpty()) {
            val nuevaRef = diagnosticosRef.push()
            diagnostico.id = nuevaRef.key ?: ""
            nuevaRef.setValue(diagnostico).await()
        } else {
            diagnosticosRef.child(diagnostico.id).setValue(diagnostico).await()
        }
    }

    // Obtener todos los diagnósticos
    suspend fun obtenerTodos(): List<Diagnostico> {
        val snapshot = diagnosticosRef.get().await()
        val lista = mutableListOf<Diagnostico>()
        for (child in snapshot.children) {
            val diagnostico = child.getValue(Diagnostico::class.java)
            diagnostico?.let { lista.add(it) }
        }
        return lista
    }

    // Obtener un diagnóstico por ID
    suspend fun obtenerPorId(id: String): Diagnostico? {
        val snapshot = diagnosticosRef.child(id).get().await()
        return snapshot.getValue(Diagnostico::class.java)
    }

    // Actualizar un diagnóstico
    suspend fun actualizarDiagnostico(diagnostico: Diagnostico) {
        diagnosticosRef.child(diagnostico.id).setValue(diagnostico).await()
    }

    // Eliminar un diagnóstico
    suspend fun eliminarDiagnostico(id: String) {
        diagnosticosRef.child(id).removeValue().await()
    }
}
