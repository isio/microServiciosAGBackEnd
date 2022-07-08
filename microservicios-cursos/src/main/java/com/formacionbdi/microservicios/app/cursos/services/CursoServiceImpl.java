package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.clients.AlumnoFeignClient;
import com.formacionbdi.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.models.repository.CursoRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

import java.util.List;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient respuestaFeignClient;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private AlumnoFeignClient alumnoFeignClient;

//    public CursoServiceImpl(AlumnoFeignClient alumnoFeignClient) {
//        this.alumnoFeignClient = alumnoFeignClient;
//    }

//    @Autowired
//    public CursoServiceImpl(CursoRepository cursoRepository) {
//        this.cursoRepository = cursoRepository;
//    }

//    @Autowired
//    public CursoServiceImpl(RespuestaFeignClient respuestaFeignClient) {
//        this.respuestaFeignClient = respuestaFeignClient;
//    }

    @Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return cursoRepository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConrespuestasAlumno(Long alumnoId) {
		return respuestaFeignClient.obtenerExamenesIdsConrespuestasAlumno(alumnoId);
	}

	@Override
	public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids) {
		return alumnoFeignClient.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		cursoRepository.eliminarCursoAlumnoPorId(id);
	}


}
