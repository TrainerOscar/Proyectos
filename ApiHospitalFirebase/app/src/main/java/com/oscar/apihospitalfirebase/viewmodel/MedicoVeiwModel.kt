package com.oscar.apihospitalfirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.repository.MedicoRepository
import kotlinx.coroutines.launch

class MedicoViewModel : ViewModel() {
    private val medicoRepository = MedicoRepository()

    fun guardarMedico(medico: Medico) {
        viewModelScope.launch {
            medicoRepository.guardarMedico(medico)
        }
    }

    fun obtenerMedicos(onResult: (List<Medico>) -> Unit) {
        viewModelScope.launch {
            val lista = medicoRepository.obtenerTodos()
            onResult(lista)
        }
    }

    fun obtenerPorId(id: String, onResult: (Medico?) -> Unit) {
        viewModelScope.launch {
            val medico = medicoRepository.obtenerPorId(id)
            onResult(medico)
        }
    }
}
