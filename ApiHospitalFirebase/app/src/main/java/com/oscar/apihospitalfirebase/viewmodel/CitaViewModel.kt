package com.oscar.apihospitalfirebase.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.repository.CitaRepository
import kotlinx.coroutines.launch

class CitaViewModel : ViewModel() {
    private val citaRepository = CitaRepository()

    fun guardarCita(
        cita: Cita,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch {
            try {
                citaRepository.guardarCita(cita)
                Log.d("CitaViewModel", "Cita guardada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al guardar cita: ${e.message}")
                onError(e)
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

    fun actualizarCita(
        cita: Cita,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                citaRepository.actualizarCita(cita)
                Log.d("CitaViewModel", "Cita actualizada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al actualizar cita: ${e.message}")
                onError(e)
            }
        }
    }

    fun eliminarCita(
        id: String,
        onSuccess: () -> Unit = {},
        onError: (Exception) -> Unit = {}
    ) {
        viewModelScope.launch {
            try {
                citaRepository.eliminarCita(id)
                Log.d("CitaViewModel", "Cita eliminada exitosamente")
                onSuccess()
            } catch (e: Exception) {
                Log.e("CitaViewModel", "Error al eliminar cita: ${e.message}")
                onError(e)
            }
        }
    }
}
