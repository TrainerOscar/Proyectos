package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.repository.PacienteRepository
import kotlinx.coroutines.launch

class PacienteViewModel : ViewModel() {

    private val repository = PacienteRepository()

    val pacientes = MutableLiveData<List<Paciente>>()
    val pacienteSeleccionado = MutableLiveData<Paciente?>()

    fun cargarPacientes() {
        viewModelScope.launch {
            try {
                pacientes.value = repository.obtenerTodos()
            } catch (e: Exception) {
                // Log error o manejarlo en UI
            }
        }
    }

    fun cargarPacientePorId(id: String) {
        viewModelScope.launch {
            try {
                pacienteSeleccionado.value = repository.obtenerPorId(id)
            } catch (e: Exception) {
                // Log error
            }
        }
    }

    fun guardarPaciente(paciente: Paciente) {
        viewModelScope.launch {
            try {
                repository.guardar(paciente)
                cargarPacientes() // Recargar lista tras guardar
            } catch (e: Exception) {
                // Log error
            }
        }
    }
}
