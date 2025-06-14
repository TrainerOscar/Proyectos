package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Receta
import com.oscar.apihospitalfirebase.repository.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {

    private val repository = RecetaRepository()

    val recetas = MutableLiveData<List<Receta>>()
    val recetaSeleccionada = MutableLiveData<Receta?>()

    fun cargarRecetas() {
        viewModelScope.launch {
            recetas.value = repository.obtenerTodos()
        }
    }

    fun cargarRecetaPorId(id: String) {
        viewModelScope.launch {
            recetaSeleccionada.value = repository.obtenerPorId(id)
        }
    }

    fun guardarReceta(receta: Receta) {
        viewModelScope.launch {
            repository.guardar(receta)
            cargarRecetas()
        }
    }
}
