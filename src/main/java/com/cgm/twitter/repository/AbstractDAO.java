package com.cgm.twitter.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Transactional
@EnableTransactionManagement
public class AbstractDAO<E> {

	@PersistenceContext(name = "Twitter")
	private EntityManager em;
	
	private final Class<E> entityClass;
	
	protected AbstractDAO(final Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional
	public EntityManager em() {
		return em;
	}
	
	@Transactional
	public E findById(final Long entityId) {
		return em.find(entityClass, entityId);
	}
	
	
	 
}
