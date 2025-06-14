package com.oscar.apihospitalfirebase.service

import org.maxmartinez.apihospital.model.Receta
import org.maxmartinez.apihospital.repository.RecetaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RecetaService @Autowired constructor(recetaRepository: RecetaRepository) {
    private val recetaRepository: RecetaRepository

    init {
        this.recetaRepository = recetaRepository
    }

    // Método para obtener todas las recetas
    fun obtenerTodasLasRecetas(): MutableList<Receta?> {
        return recetaRepository.findAll()
    }

    // Método para obtener una receta por su ID
    fun obtenerRecetaPorId(id: Long?): Optional<Receta?> {
        return recetaRepository.findById(id)
    }

    // Método para guardar una nueva receta
    fun guardarReceta(receta: Receta?): Receta {
        return recetaRepository.save(receta)
    }

    // Método para actualizar una receta existente
    fun actualizarReceta(id: Long?, recetaActualizada: Receta): Receta {
        if (recetaRepository.existsById(id)) {
            recetaActualizada.setId(id)
            return recetaRepository.save(recetaActualizada)
        } else {
            throw RuntimeException("Receta no encontrada con id " + id)
        }
    }

    // Método para eliminar una receta por su ID
    fun eliminarReceta(id: Long?) {
        if (recetaRepository.existsById(id)) {
            recetaRepository.deleteById(id)
        } else {
            throw RuntimeException("Receta no encontrada con id " + id)
        }
    }
}
