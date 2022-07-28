package com.formacionbdi.microservicios.app.respuestas.controllers;

import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.services.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
        respuestas = ((List<Respuesta>)respuestas).stream().map(r -> {
            r.setAlumnoId(r.getAlumno().getId());
            r.setPreguntaId(r.getPregunta().getId());
            return r;
        }).collect(Collectors.toList());
        Iterable<Respuesta> respuetasDb = respuestaService.saveAll(respuestas);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuetasDb);
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId) {
        Iterable<Respuesta> respuestas = respuestaService.findRespuestaByAlumnoByExamen(alumnoId,examenId);
        return ResponseEntity.ok(respuestas);
    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> obtenerExamenesIdsConrespuestasAlumno(@PathVariable Long alumnoId) {
        Iterable<Long> examenesIds = respuestaService.findExamenesIdsConRespuestasByAlumno(alumnoId);
        return ResponseEntity.ok(examenesIds);
    }
}
