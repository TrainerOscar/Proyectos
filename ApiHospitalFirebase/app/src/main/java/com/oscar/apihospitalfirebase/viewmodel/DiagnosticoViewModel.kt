package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.repository.DiagnosticoRepository
import kotlinx.coroutines.launch

class DiagnosticoViewModel : ViewModel() {

    private val repository = DiagnosticoRepository()

    val diagnosticos = MutableLiveData<List<Diagnostico>>()
    val diagnosticoSeleccionado = MutableLiveData<Diagnostico?>()

    fun cargarDiagnosticos() {
        viewModelScope.launch {
            diagnosticos.value = repository.obtenerTodos()
        }
    }

    fun cargarDiagnosticoPorId(id: String) {
        viewModelScope.launch {
            diagnosticoSeleccionado.value = repository.obtenerPorId(id)
        }
    }

    fun guardarDiagnostico(diagnostico: Diagnostico) {
        viewModelScope.launch {
            repository.guardar(diagnostico)
            cargarDiagnosticos()
        }
    }
}
