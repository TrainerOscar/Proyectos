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
            onResult(citaRepository.obtenerTodas())
        }
    }

    fun obtenerPorId(id: String, onResult: (Cita?) -> Unit) {
        viewModelScope.launch {
            onResult(citaRepository.obtenerPorId(id))
        }
    }

    fun obtenerCitasPorMedico(medicoId: String, onResult: (List<Cita>) -> Unit) {
        viewModelScope.launch {
            onResult(citaRepository.obtenerCitasPorMedico(medicoId))
        }
    }
}
