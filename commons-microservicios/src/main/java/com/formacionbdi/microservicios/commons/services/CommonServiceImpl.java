package com.formacionbdi.microservicios.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends CrudRepository<E, Long>> implements CommonService<E> {
	
	@Autowired
	protected R alumnoRepository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAll() {
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long Id) {
		return alumnoRepository.findById(Id);
	}

	@Override
	@Transactional
	public E save(E entity) {
		return alumnoRepository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long Id) {
		alumnoRepository.deleteById(Id);
	}

}
