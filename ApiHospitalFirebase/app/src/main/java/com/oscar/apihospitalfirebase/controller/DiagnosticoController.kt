package com.oscar.apihospitalfirebase.controller

import com.oscar.apihospitalfirebase.model.Diagnostico
import com.oscar.apihospitalfirebase.service.DiagnosticoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/diagnosticos")
class DiagnosticoController(
    private val diagnosticoService: DiagnosticoService
) {

    // GET: listar todos los diagnósticos
    @GetMapping
    fun listarDiagnosticos(): ResponseEntity<List<Diagnostico>> {
        val diagnosticos = diagnosticoService.obtenerTodos()
        return ResponseEntity.ok(diagnosticos)
    }

    // GET: obtener diagnóstico por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Diagnostico> {
        val diagnostico = diagnosticoService.obtenerPorId(id)
        return if (diagnostico != null) {
            ResponseEntity.ok(diagnostico)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST: crear nuevo diagnóstico
    @PostMapping
    fun crearDiagnostico(@RequestBody diagnostico: Diagnostico): ResponseEntity<Diagnostico> {
        val nuevo = diagnosticoService.guardar(diagnostico)
        return ResponseEntity.ok(nuevo)
    }
}
