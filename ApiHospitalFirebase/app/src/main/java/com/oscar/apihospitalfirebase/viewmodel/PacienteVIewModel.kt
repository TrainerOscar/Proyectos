package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.repository.PacienteRepository
import kotlinx.coroutines.launch

class PacienteViewModel : ViewModel() {
    private val pacienteRepository = PacienteRepository()

    fun guardarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            pacienteRepository.guardarPaciente(paciente)
        }
    }

    fun obtenerPacientes(onResult: (List<Paciente>) -> Unit) {
        viewModelScope.launch {
            onResult(pacienteRepository.obtenerTodos())
        }
    }

    fun obtenerPorId(id: String, onResult: (Paciente?) -> Unit) {
        viewModelScope.launch {
            onResult(pacienteRepository.obtenerPorId(id))
        }
    }
}
