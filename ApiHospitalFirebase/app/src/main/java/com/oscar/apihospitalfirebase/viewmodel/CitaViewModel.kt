package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.repository.CitaRepository
import kotlinx.coroutines.launch

class CitaViewModel : ViewModel() {
    private val citaRepository = CitaRepository()

    fun guardarCita(cita: Cita) {
        viewModelScope.launch {
            try {
                citaRepository.guardarCita(cita)
                Log.d("CitaViewModel", "Cita guardada exitosamente")
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al guardar cita: ${e.message}")
            }
        }
    }

    fun obtenerCitas(onResult: (List<Cita>) -> Unit) {
        viewModelScope.launch {
            try {
                val lista = citaRepository.obtenerTodas()
                onResult(lista)
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al obtener citas: ${e.message}")
                onResult(emptyList())
            }
        }
    }

    fun actualizarCita(cita: Cita) {
        viewModelScope.launch {
            try {
                citaRepository.actualizarCita(cita)
                Log.d("CitaViewModel", "Cita actualizada exitosamente")
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al actualizar cita: ${e.message}")
            }
        }
    }

    fun eliminarCita(id: String) {
        viewModelScope.launch {
            try {
                citaRepository.eliminarCita(id)
                Log.d("CitaViewModel", "Cita eliminada exitosamente")
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al eliminar cita: ${e.message}")
            }
        }
    }
}