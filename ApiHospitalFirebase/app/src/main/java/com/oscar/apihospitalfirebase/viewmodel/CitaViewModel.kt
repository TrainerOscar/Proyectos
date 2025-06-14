package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.repository.CitaRepository
import kotlinx.coroutines.launch

class CitaViewModel : ViewModel() {

    private val repository = CitaRepository()

    val citas = MutableLiveData<List<Cita>>()
    val citaSeleccionada = MutableLiveData<Cita?>()

    fun cargarCitas() {
        viewModelScope.launch {
            citas.value = repository.obtenerTodos()
        }
    }

    fun cargarCitaPorId(id: String) {
        viewModelScope.launch {
            citaSeleccionada.value = repository.obtenerPorId(id)
        }
    }

    fun guardarCita(cita: Cita) {
        viewModelScope.launch {
            repository.guardar(cita)
            cargarCitas()
        }
    }
}
