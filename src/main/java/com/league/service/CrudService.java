package com.league.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<DTO> {

	/**
	 * finds entity with id
	 * 
	 * @param id of the entity to return
	 * @return entity with passed id or empty optional object
	 */
	Optional<DTO> findOne(Long id);
	
	/**
	 * 
	 * 
	 * @return
	 */
	List<DTO> findAll();
	
	DTO save(DTO dto);
	
	/**
	 * 
	 * 
	 * @param id
	 * @param dto
	 * @return saved  
	 * @throws IllegalArgumentException
	 */
	DTO update(Long id, DTO dto) throws IllegalArgumentException;
	
	void delete(Long id) throws IllegalArgumentException;
	
	void setAvaialbleTo(long id, boolean available) throws IllegalArgumentException;
}
