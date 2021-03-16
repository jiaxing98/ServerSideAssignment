package service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Productline;

@Dependent
@Transactional

public class ProductlineService implements ProductlineServiceInterface {

	private EntityManager em;
	
	@Inject
	public ProductlineService(@PostGresDatabase EntityManager em) {
		this.em=em;
	}
	
	public List<Productline> getAllProductline() throws EJBException{
		return em.createNamedQuery("Productline.findAll").getResultList();
	}
	
	public List<Productline> readProductlines(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("select * from classicmodels.productlines order by productlines OFFSET ? LIMIT ?",
					Productline.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.productlines WHERE concat(productline,textdescription,htmldescription,image) LIKE ? order by productline OFFSET ? LIMIT ?",
					Productline.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		}
		List<Productline> results = q.getResultList();
		return results;
	}

	public int getNumberOfRows(String keyword) throws EJBException {
		
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.productlines");
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.productlines WHERE concat(productline,textdescription,htmldescription,image) LIKE ?");
			q.setParameter(1, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	public Productline findProductline(String productline) throws EJBException {
		
		Query q = em.createNamedQuery("Productline.findbyId");
		q.setParameter("productline",String.valueOf(productline));
		return (Productline) q.getSingleResult();
	}

	public void updateProductline(String[] s) throws EJBException {
		// Write some codes here…
		Productline p = findProductline(s[0]);

		p.setTextdescription(s[1]);
		p.setHtmldescription(s[2]);
		p.setImage(s[3]);

		em.merge(p);
	}

	public void deleteProductline(String productline) throws EJBException {
		
		Productline p = findProductline(productline);
		em.remove(p);
	}

	public void addProductline(String[] s) throws EJBException {
		

		Productline p = new Productline();
		
		p.setProductline(s[0]);
		p.setTextdescription(s[1]);
		p.setHtmldescription(s[2]);
		p.setImage(s[3]);

		em.persist(p);
	}
	
}