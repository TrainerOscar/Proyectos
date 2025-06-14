package com.oscar.apihospitalfirebase.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oscar.apihospitalfirebase.model.Receta
import kotlinx.coroutines.tasks.await

class RecetaRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("recetas")

    suspend fun obtenerTodos(): List<Receta> {
        val snapshot = collection.get().await()
        return snapshot.toObjects(Receta::class.java)
    }

    suspend fun obtenerPorId(id: String): Receta? {
        val doc = collection.document(id).get().await()
        return doc.toObject(Receta::class.java)
    }

    suspend fun guardar(receta: Receta) {
        val id = if (receta.id.isEmpty()) collection.document().id else receta.id
        val recetaConId = receta.copy(id = id)
        collection.document(id).set(recetaConId).await()
    }
}
