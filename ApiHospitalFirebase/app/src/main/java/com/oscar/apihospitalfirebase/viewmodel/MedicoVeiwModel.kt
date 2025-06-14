package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.repository.MedicoRepository
import kotlinx.coroutines.launch

class MedicoViewModel : ViewModel() {

    private val repository = MedicoRepository()

    val medicos = MutableLiveData<List<Medico>>()
    val medicoSeleccionado = MutableLiveData<Medico?>()

    fun cargarMedicos() {
        viewModelScope.launch {
            medicos.value = repository.obtenerTodos()
        }
    }

    fun cargarMedicoPorId(id: String) {
        viewModelScope.launch {
            medicoSeleccionado.value = repository.obtenerPorId(id)
        }
    }

    fun guardarMedico(medico: Medico) {
        viewModelScope.launch {
            repository.guardar(medico)
            cargarMedicos()
        }
    }
}
