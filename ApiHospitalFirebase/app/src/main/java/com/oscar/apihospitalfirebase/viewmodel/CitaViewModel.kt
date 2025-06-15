package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.repository.CitaRepository
import kotlinx.coroutines.launch

class CitaViewModel : ViewModel() {
    private val citaRepository = CitaRepository()

    fun guardarCita(cita: Cita) {
        viewModelScope.launch {
            citaRepository.guardarCita(cita)
        }
    }

    fun obtenerCitas(onResult: (List<Cita>) -> Unit) {
        viewModelScope.launch {
            val lista = citaRepository.obtenerTodas()
            onResult(lista)
        }
    }

    fun actualizarCita(cita: Cita) {
        viewModelScope.launch {
            citaRepository.actualizarCita(cita)
        }
    }

    fun eliminarCita(id: String) {
        viewModelScope.launch {
            citaRepository.eliminarCita(id)
        }
    }
}