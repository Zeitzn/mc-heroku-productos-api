package com.mc.productos.api.commons;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICrudService<T, ID> {

	/**
	 * Crea un nuevo registro
	 * @param t
	 * @return
	 */
	T register(T t);
	
	/**
	 * Actualiza los datos de un registro
	 * @param t
	 * @return
	 */
	T update(T t);
	
	/**
	 * Obtiene los datos de un registro específico
	 * @param id Identificador del registro
	 * @return
	 */
	T findById(ID id);
	
	/**
	 * Obtiene todos los registros
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * Obtiene todos los registros paginados
	 * @param pageable Datos de paginación
	 * @return
	 */
	Page<T> findAll(Pageable pageable);
	
}
