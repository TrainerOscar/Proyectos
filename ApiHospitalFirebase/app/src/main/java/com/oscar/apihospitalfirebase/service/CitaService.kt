package com.oscar.apihospitalfirebase.service

import org.maxmartinez.apihospital.model.Cita
import org.maxmartinez.apihospital.repository.CitaRepository
import org.maxmartinez.apihospital.repository.MedicoRepository
import org.maxmartinez.apihospital.repository.PacienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Optional

@Service
class CitaService {
    @Autowired
    private val citaRepository: CitaRepository? = null

    @Autowired
    private val medicoRepository: MedicoRepository? = null

    @Autowired
    private val pacienteRepository: PacienteRepository? = null

    // Listar todas las citas
    fun obtenerTodas(): MutableList<Cita?> {
        return citaRepository.findAll()
    }

    // Buscar cita por ID
    fun obtenerPorId(id: Long?): Optional<Cita?> {
        return citaRepository.findById(id)
    }

    // Validar disponibilidad del médico
    private fun validarDisponibilidadMedico(
        medicoId: Long?,
        fecha: LocalDateTime?,
        hora: LocalTime?
    ): Boolean {
        return !citaRepository.existsByMedicoIdAndFechaAndHora(medicoId, fecha, hora)
    }

    // Validar si el paciente ya tiene citas a esa hora
    private fun validarCitasPaciente(pacienteId: Long?, fecha: LocalDateTime?): Boolean {
        return citaRepository.findByPacienteIdAndFechaAfter(pacienteId, fecha).isEmpty()
    }

    // Crear o actualizar cita con validaciones
    @Throws(Exception::class)
    fun guardar(cita: Cita): Cita {
        // Validación: asegurarse de que el médico esté disponible
        if (!validarDisponibilidadMedico(
                cita.getMedico().getId(),
                cita.getFecha(),
                cita.getHora()
            )
        ) {
            throw Exception("El médico no está disponible en esa fecha y hora.")
        }

        // Validación: asegurarse de que el paciente no tenga citas duplicadas
        if (!validarCitasPaciente(cita.getPaciente().getId(), cita.getFecha())) {
            throw Exception("El paciente ya tiene una cita programada a esa hora.")
        }

        // Validación: asegurarse de que la fecha y hora sean futuras
        if (cita.getFecha().isBefore(LocalDateTime.now())) {
            throw Exception("La fecha y hora de la cita deben ser en el futuro.")
        }

        // Si todo es válido, guardamos la cita
        return citaRepository.save(cita)
    }

    // Eliminar cita
    fun eliminar(id: Long?) {
        citaRepository.deleteById(id)
    }
}
