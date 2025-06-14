package com.oscar.apihospitalfirebase.controller

import com.oscar.apihospitalfirebase.model.Cita
import com.oscar.apihospitalfirebase.service.CitaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/citas")
class CitaController(
    private val citaService: CitaService
) {
    // GET: listar todas las citas
    @GetMapping
    fun listarCitas(): ResponseEntity<List<Cita>> {
        val citas = citaService.obtenerTodas()
        return ResponseEntity.ok(citas)
    }

    // GET: obtener cita por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Cita> {
        val cita = citaService.obtenerPorId(id)
        return if (cita != null) {
            ResponseEntity.ok(cita)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST: crear nueva cita
    @PostMapping
    fun crearCita(@RequestBody cita: Cita): ResponseEntity<Any> {
        return try {
            val nuevaCita = citaService.guardar(cita)
            ResponseEntity.ok(nuevaCita)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(e.message ?: "Error desconocido")
        }
    }
}
