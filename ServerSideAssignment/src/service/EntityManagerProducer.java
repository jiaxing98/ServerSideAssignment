package service;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

	@Produces
    @PersistenceContext(unitName = "ServerSideAssignment")
    @PostGresDatabase
    private EntityManager em;

	public EntityManagerProducer() {
		super();
	}
}
