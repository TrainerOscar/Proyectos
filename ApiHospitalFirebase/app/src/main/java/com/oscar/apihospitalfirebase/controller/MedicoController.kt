package com.oscar.apihospitalfirebase.controller

import com.oscar.apihospitalfirebase.model.Medico
import com.oscar.apihospitalfirebase.service.MedicoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/medicos")
class MedicoController(
    private val medicoService: MedicoService
) {

    // GET: listar todos los médicos
    @GetMapping
    fun listarMedicos(): ResponseEntity<List<Medico>> {
        val medicos = medicoService.obtenerTodos()
        return ResponseEntity.ok(medicos)
    }

    // GET: obtener médico por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Medico> {
        val medico = medicoService.obtenerPorId(id)
        return if (medico != null) {
            ResponseEntity.ok(medico)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST: crear nuevo médico
    @PostMapping
    fun crearMedico(@RequestBody medico: Medico): ResponseEntity<Medico> {
        val nuevoMedico = medicoService.guardar(medico)
        return ResponseEntity.ok(nuevoMedico)
    }
}
