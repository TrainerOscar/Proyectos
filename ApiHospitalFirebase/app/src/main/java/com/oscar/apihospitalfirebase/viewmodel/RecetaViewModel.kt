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
    fun guardarReceta(receta: Receta) {
        viewModelScope.launch {
            try {
                recetaRepository.guardarReceta(receta)
                Log.d("RecetaViewModel", "Receta guardada exitosamente")
            } catch (e: Exception) {
                Log.e("RecetaViewModel", "Error al guardar receta: ${e.message}")
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
                onResult(emptyList()) // Retorna una lista vacía en caso de error
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
                onResult(null) // Retorna null en caso de error
            }
        }
    }
}