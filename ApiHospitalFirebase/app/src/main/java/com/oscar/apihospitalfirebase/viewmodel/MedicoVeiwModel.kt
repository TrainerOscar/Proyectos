package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.repository.MedicoRepository
import kotlinx.coroutines.launch

class MedicoViewModel : ViewModel() {
    private val medicoRepository = MedicoRepository()

    // Guardar un médico
    fun guardarMedico(medico: Medico) {
        viewModelScope.launch {
            try {
                medicoRepository.guardarMedico(medico)
                Log.d("MedicoViewModel", "Médico guardado exitosamente")
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al guardar médico: ${e.message}")
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
                onResult(emptyList()) // Retorna una lista vacía en caso de error
            }
        }
    }

    // Obtener un médico por ID
    fun obtenerPorId(id: String, onResult: (Medico?) -> Unit) {
        viewModelScope.launch {
            try {
                val medico = medicoRepository.obtenerPorId(id)
                onResult(medico)
            } catch (e: Exception) {
                Log.e("MedicoViewModel", "Error al obtener médico por ID: ${e.message}")
                onResult(null) // Retorna null en caso de error
            }
        }
    }
}