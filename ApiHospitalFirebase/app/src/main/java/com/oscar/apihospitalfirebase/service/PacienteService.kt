package com.oscar.apihospitalfirebase.service

import org.maxmartinez.apihospital.model.Paciente
import org.maxmartinez.apihospital.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class PacienteService {
    @Autowired
    private val pacienteRepository: PacienteRepository? = null

    // Listar todos
    fun obtenerTodos(): MutableList<Paciente?> {
        return pacienteRepository.findAll()
    }

    // Buscar por ID
    fun obtenerPorId(id: Long?): Optional<Paciente?> {
        return pacienteRepository.findById(id)
    }

    // Guardar nuevo o actualizar
    fun guardar(paciente: Paciente?): Paciente {
        return pacienteRepository.save(paciente)
    }

    // Eliminar
    fun eliminar(id: Long?) {
        pacienteRepository.deleteById(id)
    }
}
