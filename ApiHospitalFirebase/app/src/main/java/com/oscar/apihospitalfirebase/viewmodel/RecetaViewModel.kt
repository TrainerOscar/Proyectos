package com.oscar.apihospitalfirebase.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Receta

import com.oscar.apihospitalfirebase.repository.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {
    private val recetaRepository = RecetaRepository()

    // Guardar una receta
    fun guardarReceta(
        receta: Receta,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                recetaRepository.guardarReceta(receta)
                Log.d("RecetaViewModel", "Receta guardada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al guardar receta: ${e.message}")
                onError(e)
            }
        }
    }

    // Obtener todas las recetas
    fun obtenerRecetas(onResult: (List<Receta>) -> Unit) {
        viewModelScope.launch {
            try {
                val lista = recetaRepository.obtenerTodas()
                onResult(lista)
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al obtener recetas: ${e.message}")
                onResult(emptyList())
            }
        }
    }

    // Obtener una receta por ID
    fun obtenerPorId(id: String, onResult: (Receta?) -> Unit) {
        viewModelScope.launch {
            try {
                val receta = recetaRepository.obtenerPorId(id)
                onResult(receta)
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al obtener receta por ID: ${e.message}")
                onResult(null)
            }
        }
    }

    // Actualizar receta
    fun actualizarReceta(
        receta: Receta,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                recetaRepository.actualizarReceta(receta)
                Log.d("RecetaViewModel", "Receta actualizada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al actualizar receta: ${e.message}")
                onError(e)
            }
        }
    }

    // Eliminar receta
    fun eliminarReceta(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                recetaRepository.eliminarReceta(id)
                Log.d("RecetaViewModel", "Receta eliminada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al eliminar receta: ${e.message}")
                onError(e)
            }
        }
    }
}
