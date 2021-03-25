package services;

import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import services.PostGresDatabase;
import domain.Productline;

@Dependent
@Transactional
public class ProductlineService implements ProductlineServiceInterface{
	
	private EntityManager em;

	@Inject
	public ProductlineService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}
	
	public List<Productline> getAllProductline() throws EJBException {
		// Write some codes here…
		return em.createNamedQuery("Productline.findAll").getResultList();
	}

}
