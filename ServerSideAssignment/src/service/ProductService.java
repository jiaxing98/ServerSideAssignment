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
import domain.Productline;

@Dependent
@Transactional
public class ProductService implements ProductServiceInterface {

	private EntityManager em;

	@Inject
	private ProductlineService plineser;

	@Inject
	public ProductService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Product> getAllProduct() throws EJBException {
		return em.createNamedQuery("Product.findAll").getResultList();
	}

	@Override
	public Product findProduct(String productcode) throws EJBException {
		Query q = em.createNamedQuery("Product.findbyId");
		q.setParameter("productcode", String.valueOf(productcode));
		return (Product) q.getSingleResult();
	}
	
	@Override
	public String findProductCode(String productName) throws EJBException {
		Query q = em.createNamedQuery("Product.findpCode");
		q.setParameter("productname", String.valueOf(productName));
		return (String) q.getSingleResult();
	}

	@Override
	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword, String pdlSelected) throws EJBException {
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

	@Override
	public int getNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.products");

		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.products WHERE concat(productcode,productname) LIKE ?");

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public boolean updateProduct(String[] s) throws EJBException {
		Product product = findProduct(s[0]);

		Productline productl = plineser.findProductline(s[2]);

		product.setProductname(s[1]);
		product.setProductlineBean(productl);
		product.setProductscale(s[3]);
		product.setProductvendor(s[4]);
		product.setProductdescription(s[5]);
		product.setQuantityinstock(Integer.parseInt(s[6]));
		product.setBuyprice(s[7].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[7]));
		product.setMsrp(s[8].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[8]));

		em.merge(product);
		return true;
	}

	@Override
	public void deleteProduct(String productcode) throws EJBException {
		Product product = findProduct(productcode);
		em.remove(product);
	}

	@Override
	public boolean addProduct(String[] s) throws EJBException {
		Product product = new Product();
		Productline pl = plineser.findProductline(s[2]);

		product.setProductcode(s[0]);
		product.setProductname(s[1]);
		product.setProductlineBean(pl);
		product.setProductscale(s[3]);
		product.setProductvendor(s[4]);
		product.setProductdescription(s[5]);
		product.setQuantityinstock(Integer.parseInt(s[6]));
		product.setBuyprice(s[7].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[7]));
		product.setMsrp(s[8].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[8]));

		em.persist(product);
		return true;
	}

}