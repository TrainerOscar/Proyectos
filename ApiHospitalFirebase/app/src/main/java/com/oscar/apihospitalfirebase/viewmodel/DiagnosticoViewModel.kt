package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.repository.DiagnosticoRepository
import kotlinx.coroutines.launch

class DiagnosticoViewModel : ViewModel() {

    private val diagnosticoRepository = DiagnosticoRepository()

    // Guardar diagnóstico
    fun guardarDiagnostico(
        diagnostico: Diagnostico,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                diagnosticoRepository.guardarDiagnostico(diagnostico)
                Log.d("DiagnosticoViewModel", "Diagnóstico guardado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al guardar diagnóstico: ${e.message}")
                onError(e)
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
                onResult(emptyList())
            }
        }
    }

    // Obtener diagnóstico por ID
    fun obtenerPorId(id: String, onResult: (Diagnostico?) -> Unit) {
        viewModelScope.launch {
            try {
                val diagnostico = diagnosticoRepository.obtenerPorId(id)
                onResult(diagnostico)
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al obtener diagnóstico por ID: ${e.message}")
                onResult(null)
            }
        }
    }

    // Actualizar diagnóstico
    fun actualizarDiagnostico(
        diagnostico: Diagnostico,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                diagnosticoRepository.actualizarDiagnostico(diagnostico)
                Log.d("DiagnosticoViewModel", "Diagnóstico actualizado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al actualizar diagnóstico: ${e.message}")
                onError(e)
            }
        }
    }

    // Eliminar diagnóstico
    fun eliminarDiagnostico(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                diagnosticoRepository.eliminarDiagnostico(id)
                Log.d("DiagnosticoViewModel", "Diagnóstico eliminado exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("DiagnosticoViewModel", "Error al eliminar diagnóstico: ${e.message}")
                onError(e)
            }
        }
    }
}
