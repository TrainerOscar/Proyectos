package com.oscar.apihospitalfirebase.controller

import com.oscar.apihospitalfirebase.model.Paciente
import com.oscar.apihospitalfirebase.service.PacienteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pacientes")
class PacienteController(
    private val pacienteService: PacienteService
) {

    // GET: listar todos los pacientes
    @GetMapping
    fun listarPacientes(): ResponseEntity<List<Paciente>> {
        val pacientes = pacienteService.obtenerTodos()
        return ResponseEntity.ok(pacientes)
    }

    // GET: obtener paciente por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Paciente> {
        val paciente = pacienteService.obtenerPorId(id)
        return if (paciente != null) {
            ResponseEntity.ok(paciente)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST: crear nuevo paciente
    @PostMapping
    fun crearPaciente(@RequestBody paciente: Paciente): ResponseEntity<Paciente> {
        val nuevo = pacienteService.guardar(paciente)
        return ResponseEntity.ok(nuevo)
    }
}
