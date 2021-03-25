package services;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Product;

@Dependent
@Transactional
public class ProductService implements ProductServiceInterface {

	private EntityManager em;

	@Inject
	public ProductService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}

	public List<Product> getAllProduct() throws EJBException {
		// Write some codes here…
		return em.createNamedQuery("Product.findAll").getResultList();
	}

	public int getNumberOfProduct(String keyword) throws EJBException {
		// Write some codes here…
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.products");
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.products WHERE concat(productline, productname) LIKE ?");
			q.setParameter(1, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword, String pdlSelected)
			throws EJBException {
		// Write some codes here…
		Query q = null;
		if (pdlSelected.isEmpty() && keyword.isEmpty()) {
			q = em.createNativeQuery("select * from classicmodels.products order by productline OFFSET ? LIMIT ?",
					Product.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else if (!pdlSelected.isEmpty() && keyword.isEmpty()) {
			q = em.createNativeQuery(
					"select * from classicmodels.products WHERE productline= ? order by productline OFFSET ? LIMIT ?",
					Product.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "'" + pdlSelected + "'");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		} else if (pdlSelected.isEmpty() && !keyword.isEmpty()) {
			q = em.createNativeQuery(
					"select * from classicmodels.products WHERE concat(productline, productname) LIKE ? order by productline OFFSET ? LIMIT ?",
					Product.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.products WHERE productline= ? AND concat(productline, productname) LIKE ? order by productline OFFSET ? LIMIT ?",
					Product.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "'" + pdlSelected + "'");
			q.setParameter(2, "%" + keyword + "%");
			q.setParameter(3, Integer.valueOf(start));
			q.setParameter(4, Integer.valueOf(recordsPerPage));
		}
		List<Product> results = q.getResultList();
		return results;
	}

}
