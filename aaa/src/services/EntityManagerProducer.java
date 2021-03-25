package services;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import services.PostGresDatabase;

public class EntityManagerProducer {
	@Produces
	@PersistenceContext(unitName = "aaa")
	@PostGresDatabase
	private EntityManager em;
	
	public EntityManagerProducer() {
		super();
	}

}
