package service;

import java.math.BigDecimal;
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

	@Override
	public List<Product> getAllProduct() throws EJBException {
		return em.createNamedQuery("Product.findAll").getResultList();
	}

	@Override
	public Product findProduct(String id) throws EJBException {
		Query q = em.createNamedQuery("Product.findbyId");
		q.setParameter("id", String.valueOf(id));
		return (Product) q.getSingleResult();
	}

	@Override
	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.products order by productcode OFFSET ? LIMIT ?",
					Product.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.products WHERE concat(productcode,productname,productline,productvendor) LIKE ? order by productcode OFFSET ? LIMIT ?",
					Product.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		List<Product> results = q.getResultList();
		return results;
	}

	@Override
	public int getNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.products");

		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.products"
					+ "WHERE concat(productcode,productname) LIKE ?");	//search condition

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public void updateProduct(String[] s) throws EJBException {
		Product product = findProduct(s[0]);

		product.setProductname(s[1]);
		//product.setProductlineBean().setProductline()(s[2]);
		product.setProductvendor(s[3]);
		product.setProductdescription(s[4]);
		//product.setQuantityinstock(s[5]);
		//product.setBuyprice(s[6]);
		//product.setMsrp(s[7]);
		
		em.merge(product);
	}

	@Override
	public void deleteProduct(String id) throws EJBException {
		Product product= findProduct(id);
		em.remove(product);
	}

	@Override
	public void addProduct(String[] s) throws EJBException {
		Product product = new Product();

		
		product.setProductname(s[1]);
		//product.setProductlineBean(s[2]);
		product.setProductvendor(s[3]);
		product.setProductdescription(s[4]);
		//product.setQuantityinstock(s[5]);
		//product.setBuyprice(s[6]);
		//product.setMsrp(s[7]);
		
		em.persist(product);
	}

}
