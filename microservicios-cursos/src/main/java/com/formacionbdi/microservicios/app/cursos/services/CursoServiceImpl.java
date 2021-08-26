package com.formacionbdi.microservicios.app.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.microservicios.app.cursos.models.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.models.repository.CursoRepository;
import com.formacionbdi.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long Id) {
		return cursoRepository.findCursoByAlumnoId(Id);
	}


}
