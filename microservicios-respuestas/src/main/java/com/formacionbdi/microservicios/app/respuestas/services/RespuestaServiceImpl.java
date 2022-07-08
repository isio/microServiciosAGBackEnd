package com.formacionbdi.microservicios.app.respuestas.services;

import com.formacionbdi.microservicios.app.respuestas.clients.ExamenFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Pregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private ExamenFeignClient examenFeignClient;

//    public RespuestaServiceImpl(ExamenFeignClient examenFeignClient) {
//        this.examenFeignClient = examenFeignClient;
//    }
//
//    @Autowired
//    public RespuestaServiceImpl(RespuestaRepository respuestaRepository) {
//        this.respuestaRepository = respuestaRepository;
//    }

    @Override
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
        return respuestaRepository.saveAll(respuestas);
    }

    @Override
    public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId) {
        Examen examen = examenFeignClient.ObtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntaIds = preguntas.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByPreguntaId(alumnoId, preguntaIds);
        respuestas = respuestas.stream().map(r -> {
            preguntas.forEach(p -> {
                if(p.getId() == r.getPreguntaId()) {
                    r.setPregunta(p);
                }
            });
            return r;
        }).collect(Collectors.toList());
        return respuestas;
    }

    @Override
    public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
        return null;
    }
}
