package br.rafaelhorochovec.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.rafaelhorochovec.jpa.jpautil.JPAUtil;

public class DaoGeneric<MODEL> {

	public void salvar(MODEL model) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(model);
		entityTransaction.commit();
		entityManager.close();
	}

	public MODEL merge(MODEL model) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		MODEL retorno = entityManager.merge(model);
		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}

	public void delete(MODEL model) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(model);
		entityTransaction.commit();
		entityManager.close();
	}

	public void deletePorId(MODEL model) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(model);
		entityManager.createQuery("delete from " + model.getClass().getCanonicalName() + " where id = " + id).executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	public List<MODEL> getListModel(Class<MODEL> model) {
		EntityManager entityManager = JPAUtil.geEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		List<MODEL> retorno = entityManager.createQuery("from " + model.getName()).getResultList();

		entityTransaction.commit();
		entityManager.close();
		return retorno;
	}
}
