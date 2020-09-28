package br.com.psg.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.psg.utils.ClassName;
import br.com.psg.utils.JPAUltil;

public class DaoGeneric<E> {

	// Generic method that will create a new register in database
	public void save(E entity) {
		EntityManager entityManager = JPAUltil.getEntityManager();

		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(entity);

		entityTransaction.commit();
		entityManager.close();
	}

	public E updateMerge(E entity) {
		EntityManager entityManager = JPAUltil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		E entitySaved = entityManager.merge(entity);
		transaction.commit();

		return entitySaved;
	}

	// Generic method that will get all registers in database
	public List<E> getAll(Class<E> entity) {
		EntityManager entityManager = JPAUltil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		@SuppressWarnings("unchecked")
		List<E> entityList = entityManager.createQuery(" from " + new ClassName<E>().getClassName(entity))
				.getResultList();

		entityTransaction.commit();
		entityManager.close();

		return entityList;

	}

	// Generic method that will get by id in database
	public E getById(E entity) {
		EntityManager entityManager = JPAUltil.getEntityManager();

		Object id = JPAUltil.getPrimaryKey(entity);

		@SuppressWarnings("unchecked")
		E e = (E) entityManager.find(entity.getClass(), id);

		return e;

	}

	public void deleteById(E entity) {
		Object id = JPAUltil.getPrimaryKey(entity);
		EntityManager entityManager = JPAUltil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();

		entityManager
				.createQuery("delete from " + entity.getClass().getSimpleName()+ " where id = " + id)
				.executeUpdate();

		transaction.commit();

	}
}