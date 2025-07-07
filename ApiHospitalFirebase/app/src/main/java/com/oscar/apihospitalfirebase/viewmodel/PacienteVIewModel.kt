package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.repository.PacienteRepository
import kotlinx.coroutines.launch

class PacienteViewModel : ViewModel() {

    private val pacienteRepository = PacienteRepository()

    // Guardar paciente
    fun guardarPaciente(
        paciente: Paciente,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                pacienteRepository.guardarPaciente(paciente)
                Log.d("PacienteViewModel", "Paciente guardado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al guardar paciente: ${e.message}")
                onError(e)
            }
        }
    }

    // Actualizar paciente
    fun actualizarPaciente(
        paciente: Paciente,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                pacienteRepository.actualizarPaciente(paciente)
                Log.d("PacienteViewModel", "Paciente actualizado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al actualizar paciente: ${e.message}")
                onError(e)
            }
        }
    }

    // Eliminar paciente
    fun eliminarPaciente(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                pacienteRepository.eliminarPaciente(id)
                Log.d("PacienteViewModel", "Paciente eliminado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al eliminar paciente: ${e.message}")
                onError(e)
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
                onResult(emptyList())
            }
        }
    }

    // Obtener paciente por ID
    fun obtenerPorId(id: String, onResult: (Paciente?) -> Unit) {
        viewModelScope.launch {
            try {
                val paciente = pacienteRepository.obtenerPorId(id)
                onResult(paciente)
            } catch (e: Exception) {
                Log.e("PacienteViewModel", "Error al obtener paciente por ID: ${e.message}")
                onResult(null)
            }
        }
    }
}
