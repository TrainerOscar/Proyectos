package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.repository.DiagnosticoRepository
import kotlinx.coroutines.launch

class DiagnosticoViewModel : ViewModel() {
    private val diagnosticoRepository = DiagnosticoRepository()

    fun guardarDiagnostico(diagnostico: Diagnostico) {
        viewModelScope.launch {
            diagnosticoRepository.guardarDiagnostico(diagnostico)
        }
    }

    fun obtenerDiagnosticos(onResult: (List<Diagnostico>) -> Unit) {
        viewModelScope.launch {
            onResult(diagnosticoRepository.obtenerTodos())
        }
    }

    fun obtenerPorId(id: String, onResult: (Diagnostico?) -> Unit) {
        viewModelScope.launch {
            onResult(diagnosticoRepository.obtenerPorId(id))
        }
    }
}
