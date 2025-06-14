package com.oscar.apihospitalfirebase.service

import org.maxmartinez.apihospital.model.Diagnostico
import org.maxmartinez.apihospital.repository.DiagnosticoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class DiagnosticoService {
    @Autowired
    private val diagnosticoRepository: DiagnosticoRepository? = null

    // Listar todos
    fun obtenerTodos(): MutableList<Diagnostico?> {
        return diagnosticoRepository.findAll()
    }

    // Buscar por ID
    fun obtenerPorId(id: Long?): Optional<Diagnostico?> {
        return diagnosticoRepository.findById(id)
    }

    // Guardar nuevo
    fun guardar(diagnostico: Diagnostico?): Diagnostico {
        return diagnosticoRepository.save(diagnostico)
    }

    // Eliminar
    fun eliminar(id: Long?) {
        diagnosticoRepository.deleteById(id)
    }
}