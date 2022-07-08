package com.formacionbdi.microservicios.app.cursos.services;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.commons.alumnos.models.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.CommonService;

import java.util.List;

public interface CursoService extends CommonService<Curso> {
	public Curso findCursoByAlumnoId(Long Id);

	public Iterable<Long> obtenerExamenesIdsConrespuestasAlumno(Long alumnoId);

	public Iterable<Alumno> obtenerAlumnosPorCurso(Iterable<Long> ids);

	public void eliminarCursoAlumnoPorId(Long id);
}
