package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Receta
import com.oscar.apihospitalfirebase.repository.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {
    private val recetaRepository = RecetaRepository()

    fun guardarReceta(receta: Receta) {
        viewModelScope.launch {
            recetaRepository.guardarReceta(receta)
        }
    }

    fun obtenerRecetas(onResult: (List<Receta>) -> Unit) {
        viewModelScope.launch {
            onResult(recetaRepository.obtenerTodas())
        }
    }

    fun obtenerPorId(id: String, onResult: (Receta?) -> Unit) {
        viewModelScope.launch {
            onResult(recetaRepository.obtenerPorId(id))
        }
    }
}
