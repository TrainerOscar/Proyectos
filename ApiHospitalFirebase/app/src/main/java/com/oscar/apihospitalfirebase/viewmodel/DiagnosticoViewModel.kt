package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.repository.DiagnosticoRepository
import kotlinx.coroutines.launch

class DiagnosticoViewModel : ViewModel() {
    private val diagnosticoRepository = DiagnosticoRepository()

    // Guardar un diagnóstico
    fun guardarDiagnostico(diagnostico: Diagnostico) {
        viewModelScope.launch {
            try {
                diagnosticoRepository.guardarDiagnostico(diagnostico)
                Log.d("DiagnosticoViewModel", "Diagnóstico guardado exitosamente")
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al guardar diagnóstico: ${e.message}")
            }
        }
    }

    // Obtener todos los diagnósticos
    fun obtenerDiagnosticos(onResult: (List<Diagnostico>) -> Unit) {
        viewModelScope.launch {
            try {
                val lista = diagnosticoRepository.obtenerTodos()
                onResult(lista)
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al obtener diagnósticos: ${e.message}")
                onResult(emptyList()) // Retorna una lista vacía en caso de error
            }
        }
    }

    // Obtener un diagnóstico por ID
    fun obtenerPorId(id: String, onResult: (Diagnostico?) -> Unit) {
        viewModelScope.launch {
            try {
                val diagnostico = diagnosticoRepository.obtenerPorId(id)
                onResult(diagnostico)
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al obtener diagnóstico por ID: ${e.message}")
                onResult(null) // Retorna null en caso de error
            }
        }
    }
}