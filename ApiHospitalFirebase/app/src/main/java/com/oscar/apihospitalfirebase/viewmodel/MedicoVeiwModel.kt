package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.repository.MedicoRepository
import kotlinx.coroutines.launch

class MedicoViewModel : ViewModel() {

    private val medicoRepository = MedicoRepository()

    // Guardar médico
    fun guardarMedico(
        medico: Medico,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                medicoRepository.guardarMedico(medico)
                Log.d("MedicoViewModel", "Médico guardado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al guardar médico: ${e.message}")
                onError(e)
            }
        }
    }

    // Obtener todos los médicos
    fun obtenerMedicos(onResult: (List<Medico>) -> Unit) {
        viewModelScope.launch {
            try {
                val lista = medicoRepository.obtenerTodos()
                onResult(lista)
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al obtener médicos: ${e.message}")
                onResult(emptyList())
            }
        }
    }

    // Obtener médico por ID
    fun obtenerPorId(id: String, onResult: (Medico?) -> Unit) {
        viewModelScope.launch {
            try {
                val medico = medicoRepository.obtenerPorId(id)
                onResult(medico)
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al obtener médico por ID: ${e.message}")
                onResult(null)
            }
        }
    }

    // Actualizar médico
    fun actualizarMedico(
        medico: Medico,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                medicoRepository.actualizarMedico(medico)
                Log.d("MedicoViewModel", "Médico actualizado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al actualizar médico: ${e.message}")
                onError(e)
            }
        }
    }

    // Eliminar médico
    fun eliminarMedico(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                medicoRepository.eliminarMedico(id)
                Log.d("MedicoViewModel", "Médico eliminado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al eliminar médico: ${e.message}")
                onError(e)
            }
        }
    }
}
