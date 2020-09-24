package br.com.psg.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUltil {

	private static EntityManagerFactory factory = null;

	static {
		try {
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("market");
			}
		} catch (Exception e) {
			System.out.println("Erro JPAUltil 001: " + e.getMessage());
		}

	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
