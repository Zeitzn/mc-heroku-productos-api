package com.mc.productos.api.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericDAO<T, ID> extends JpaRepository<T, ID> {

}
