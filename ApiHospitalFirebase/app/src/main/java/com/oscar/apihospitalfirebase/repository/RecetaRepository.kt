package com.oscar.apihospitalfirebase.repository


import com.google.firebase.database.FirebaseDatabase
import com.oscar.apihospitalfirebase.model.Receta
import kotlinx.coroutines.tasks.await

class  RecetaRepository {
    private val db = FirebaseDatabase.getInstance()
    private val recetaRef = db.getReference("recetas")

    // Guardar una nueva receta
    suspend fun guardarReceta(receta: Receta) {
        if (receta.id == null) {
            val nuevaRef = recetaRef.push()
            receta.id = nuevaRef.key.toString()
            nuevaRef.setValue(receta).await()
        } else {
            recetaRef.child(receta.id!!).setValue(receta).await()
        }
    }

    // Obtener todas las recetas
    suspend fun obtenerTodas(): List<Receta> {
        val snapshot = recetaRef.get().await()
        val lista = mutableListOf<Receta>()
        for (child in snapshot.children) {
            val receta = child.getValue(Receta::class.java)
            receta?.let { lista.add(it) }
        }
        return lista
    }

    // Obtener una receta por ID
    suspend fun obtenerPorId(id: String): Receta? {
        val snapshot = recetaRef.child(id).get().await()
        return snapshot.getValue(Receta::class.java)
    }

    // Actualizar una receta
    suspend fun actualizarReceta(receta: Receta) {
        receta.id?.let {
            recetaRef.child(it).setValue(receta).await()
        }
    }

    // Eliminar una receta
    suspend fun eliminarReceta(id: String) {
        recetaRef.child(id).removeValue().await()
    }
}
