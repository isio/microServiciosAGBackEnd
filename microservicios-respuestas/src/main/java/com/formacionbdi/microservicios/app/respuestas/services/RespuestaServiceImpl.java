package com.formacionbdi.microservicios.app.respuestas.services;

// import com.formacionbdi.microservicios.app.respuestas.clients.ExamenFeignClient;
import com.formacionbdi.microservicios.app.respuestas.models.entity.Respuesta;
import com.formacionbdi.microservicios.app.respuestas.models.repository.RespuestaRepository;
// import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;
// import com.formacionbdi.microservicios.commons.examenes.models.entity.Pregunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    private RespuestaRepository respuestaRepository;

    // @Autowired
    // private ExamenFeignClient examenFeignClient;

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
        /*Examen examen = examenFeignClient.obtenerExamenPorId(examenId);
        List<Pregunta> preguntas = examen.getPreguntas();
        List<Long> preguntaIds = preguntas.stream().map(p -> p.getId()).collect(Collectors.toList());
        List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByPreguntaIds(alumnoId, preguntaIds);
        respuestas = respuestas.stream().map(r -> {
            preguntas.forEach(p -> {
                if(p.getId() == r.getPreguntaId()) {
                    r.setPregunta(p);
                }
            });
            return r;
        }).collect(Collectors.toList());*/

        List<Respuesta> respuestas = (List<Respuesta>) respuestaRepository.findRespuestaByAlumnoByExamen(alumnoId, examenId);
        return respuestas;
    }

	@Override
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		/*List<Respuesta> respuestasAlumno = (List<Respuesta>) respuestaRepository.findByAlumnoId(alumnoId);
		List<Long> examenIds = Collections.emptyList();
		
		if(respuestasAlumno.size() > 0) {
		  List<Long> preguntaIds = respuestasAlumno.stream().map(r -> r.getPreguntaId()).collect(Collectors.toList());
		  examenIds = examenFeignClient.obtenerExamenesIdsPorPreguntasIdRespondidas(preguntaIds);
		}*/

		List<Respuesta> respuestasAlumno = (List<Respuesta>) respuestaRepository.findExamenesIdsConRespuestasByAlumno(alumnoId);
		List<Long> examenIds = respuestasAlumno
				.stream()
				.map(r -> r.getPregunta().getExamen().getId())
				.distinct()
				.collect(Collectors.toList());
		return examenIds;
	}

	@Override
	public Iterable<Respuesta> findByAlumnoId(Long alumnoId) {
		return respuestaRepository.findByAlumnoId(alumnoId);
	}

}
