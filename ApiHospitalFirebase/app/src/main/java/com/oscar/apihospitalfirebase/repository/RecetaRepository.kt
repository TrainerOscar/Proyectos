package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.oscar.apihospitalfirebase.model.Receta
import kotlinx.coroutines.tasks.await

class RecetaRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("recetas")

    suspend fun guardarReceta(receta: Receta) {
        receta.id?.let {
            collection.document(it).set(receta).await()
        } ?: run {
            collection.add(receta).await()
        }
    }

    suspend fun obtenerTodas(): List<Receta> {
        return collection.get().await().toObjects(Receta::class.java)
    }

    suspend fun obtenerPorId(id: String): Receta? {
        return collection.document(id).get().await().toObject(Receta::class.java)
    }
}
