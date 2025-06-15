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

    fun actualizarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            pacienteRepository.actualizarPaciente(paciente)
        }
    }

    fun eliminarPaciente(id: String) {
        viewModelScope.launch {
            pacienteRepository.eliminarPaciente(id)
        }
    }

    fun obtenerPacientes(onResult: (List<Paciente>) -> Unit) {
        viewModelScope.launch {
            val lista = pacienteRepository.obtenerTodos()
            onResult(lista)
        }
    }

    fun obtenerPorId(id: String, onResult: (Paciente?) -> Unit) {
        viewModelScope.launch {
            val paciente = pacienteRepository.obtenerPorId(id)
            onResult(paciente)
        }
    }
}
