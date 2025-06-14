package com.oscar.apihospitalfirebase.controller

import com.oscar.apihospitalfirebase.model.Receta
import com.oscar.apihospitalfirebase.service.RecetaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recetas")
class RecetaController(
    private val recetaService: RecetaService
) {

    // GET: listar todas las recetas
    @GetMapping
    fun listarRecetas(): ResponseEntity<List<Receta>> {
        val recetas = recetaService.obtenerTodasLasRecetas()
        return ResponseEntity.ok(recetas)
    }

    // GET: obtener receta por ID
    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: String): ResponseEntity<Receta> {
        val receta = recetaService.obtenerRecetaPorId(id)
        return if (receta != null) {
            ResponseEntity.ok(receta)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // POST: crear nueva receta
    @PostMapping
    fun crearReceta(@RequestBody receta: Receta): ResponseEntity<Receta> {
        val nuevaReceta = recetaService.guardarReceta(receta)
        return ResponseEntity.ok(nuevaReceta)
    }
}
