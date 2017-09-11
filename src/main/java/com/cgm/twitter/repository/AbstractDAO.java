package com.cgm.twitter.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cgm.twitter.domain.Message;
import com.cgm.twitter.domain.User;

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
	public void save(final E entityToBeSaved) {
		em.persist(entityToBeSaved);
	}

	@Transactional
	public void update(final E entityToBeUpdated) {
		em.merge(entityToBeUpdated);
	}

	@Transactional
	public void remove(final E entityToBeRemoved) {
		em.remove(entityToBeRemoved);
	}

	@Transactional
	public void delete(final Long id) {
		em.remove(findById(id));
	}
	
	@Transactional
	public E findById(final Long entityId) {
		return em.find(entityClass, entityId);
	}
	
	@Transactional
	public E findByUsername(final String entityName) throws NoResultException {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityClass);
		Root<E> root = query.from(entityClass);
		
		query.select(root);
		query.where(builder.equal(root.get("username"), entityName));
		TypedQuery<E> typedQuery = em.createQuery(query);
		E result = typedQuery.getSingleResult();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findAllMessages(User user_id) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityClass);
		Root<E> root = query.from(entityClass);
		
		query.select(root);
		query.where(builder.equal(root.get("user"), user_id.getId()));
		TypedQuery<E> q = em.createQuery(query);
		List<E> result = q.getResultList();
		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<E> findAllUsers(String username) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<E> query = builder.createQuery(entityClass);
		Root<E> root = query.from(entityClass);
		
		query.select(root);
		query.where(builder.notEqual(root.get("username"), username));
		TypedQuery<E> q = em.createQuery(query);
		List<E> result = q.getResultList();
		return result;
		
	}
	
	@Transactional
	public void addFriend(User user, User newUser) {
		user.getUser().add(newUser);
		em.merge(user);
	}
	
	@Transactional
	public void removeFriend(User user,User removeUser) {
		user.getUser().remove(removeUser);
		em.merge(user);
	}
	
}
