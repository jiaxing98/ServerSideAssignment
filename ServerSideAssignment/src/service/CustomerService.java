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

import domain.Customer;

@Dependent
@Transactional
public class CustomerService implements CustomerServiceInterface{
	
	private EntityManager em;

	@Inject
    public CustomerService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }

	@Override
	public List<Customer> getAllCustomers() throws EJBException {
		return em.createNamedQuery("Customer.findAll").getResultList();
	}

	@Override
	public Customer findCustomer(String id) throws EJBException {
		Query q = em.createNamedQuery("Customer.findbyId");
		q.setParameter("id", Long.valueOf(id));
		return (Customer) q.getSingleResult();
	}

	@Override
	public List<Customer> readPayment(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.customer order by id OFFSET ? LIMIT ?",
					Customer.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.customer WHERE concat(id,first_name,last_name,gender) LIKE ? order by id OFFSET ? LIMIT ?",
					Customer.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		List<Customer> results = q.getResultList();
		return results;
	}

	@Override
	public int getNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.customer");

		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.customer "
					+ "WHERE concat(customernumber,customername) LIKE ?");	//search condition

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public void updateCustomer(String[] s) throws EJBException {
		Customer customer = findCustomer(s[0]);

		customer.setAddressline1(s[1]);
		customer.setAddressline2(s[2]);
		customer.setCity(s[3]);
		customer.setContactfirstname(s[4]);
		customer.setContactlastname(s[5]);
		customer.setCountry(s[6]);
		customer.setCreditlimit(new BigDecimal(s[7]));

		customer.setCustomername(s[8]);
		customer.setPhone(s[9]);
		customer.setPostalcode(s[10]);
		customer.setState(s[11]);

		em.merge(customer);
	}

	@Override
	public void deleteCustomer(String id) throws EJBException {
		Customer customer = findCustomer(id);
		em.remove(customer);
	}

	@Override
	public void addCustomer(String[] s) throws EJBException {
		Customer customer = new Customer();

		customer.setAddressline1(s[1]);
		customer.setAddressline2(s[2]);
		customer.setCity(s[3]);
		customer.setContactfirstname(s[4]);
		customer.setContactlastname(s[5]);
		customer.setCountry(s[6]);
		customer.setCreditlimit(new BigDecimal(s[7]));

		customer.setCustomername(s[8]);
		customer.setPhone(s[9]);
		customer.setPostalcode(s[10]);
		customer.setState(s[11]);

		em.persist(customer);
	}
}
