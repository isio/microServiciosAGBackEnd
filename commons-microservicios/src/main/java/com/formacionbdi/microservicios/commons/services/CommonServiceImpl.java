package com.formacionbdi.microservicios.commons.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements CommonService<E> {
	
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

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return alumnoRepository.findAll(pageable);
	}

}
