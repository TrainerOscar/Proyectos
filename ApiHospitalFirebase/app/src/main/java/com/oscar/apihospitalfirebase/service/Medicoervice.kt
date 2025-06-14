package com.oscar.apihospitalfirebase.service

import org.maxmartinez.apihospital.model.Medico
import org.maxmartinez.apihospital.repository.MedicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MedicoService {
    @Autowired
    private val medicoRepository: MedicoRepository? = null

    // Obtener todos los médicos
    fun obtenerTodos(): MutableList<Medico?> {
        return medicoRepository.findAll()
    }

    // Obtener médico por ID
    fun obtenerPorId(id: Long?): Optional<Medico?> {
        return medicoRepository.findById(id)
    }

    // Guardar médico nuevo o actualizar
    fun guardar(medico: Medico?): Medico {
        return medicoRepository.save(medico)
    }

    // Eliminar médico
    fun eliminar(id: Long?) {
        medicoRepository.deleteById(id)
    }
}