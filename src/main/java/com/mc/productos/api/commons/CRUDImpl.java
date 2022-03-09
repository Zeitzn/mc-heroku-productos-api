package com.mc.productos.api.commons;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class CRUDImpl<T,ID> implements ICrudService<T, ID> {

	protected abstract IGenericDAO<T, ID> dao();
	
	@Override
	public T register(T entity) {
		return dao().save(entity);
	}

	@Override
	public T update(T entity) {
		return dao().save(entity);
	}

	@Override
	public T findById(ID id) {
		return dao().findById(id).orElse(null);
	}

	@Override
	public List<T> findAll() {
		return dao().findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return dao().findAll(pageable);
	}

}
