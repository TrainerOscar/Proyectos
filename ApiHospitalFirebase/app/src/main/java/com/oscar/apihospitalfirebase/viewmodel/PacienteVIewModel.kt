package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.repository.PacienteRepository
import kotlinx.coroutines.launch

class PacienteViewModel : ViewModel() {
    private val pacienteRepository = PacienteRepository()

    // Guardar un paciente
    fun guardarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            try {
                pacienteRepository.guardarPaciente(paciente)
                Log.d("PacienteViewModel", "Paciente guardado exitosamente")
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al guardar paciente: ${e.message}")
            }
        }
    }

    // Actualizar un paciente
    fun actualizarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            try {
                pacienteRepository.actualizarPaciente(paciente)
                Log.d("PacienteViewModel", "Paciente actualizado exitosamente")
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al actualizar paciente: ${e.message}")
            }
        }
    }

    // Eliminar un paciente
    fun eliminarPaciente(id: String) {
        viewModelScope.launch {
            try {
                pacienteRepository.eliminarPaciente(id)
                Log.d("PacienteViewModel", "Paciente eliminado exitosamente")
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al eliminar paciente: ${e.message}")
            }
        }
    }

    // Obtener todos los pacientes
    fun obtenerPacientes(onResult: (List<Paciente>) -> Unit) {
        viewModelScope.launch {
            try {
                val lista = pacienteRepository.obtenerTodos()
                onResult(lista)
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al obtener pacientes: ${e.message}")
                onResult(emptyList()) // Retorna una lista vacía en caso de error
            }
        }
    }

    // Obtener un paciente por ID
    fun obtenerPorId(id: String, onResult: (Paciente?) -> Unit) {
        viewModelScope.launch {
            try {
                val paciente = pacienteRepository.obtenerPorId(id)
                onResult(paciente)
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al obtener paciente por ID: ${e.message}")
                onResult(null) // Retorna null en caso de error
            }
        }
    }
}