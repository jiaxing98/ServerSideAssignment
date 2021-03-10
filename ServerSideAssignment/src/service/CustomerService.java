package service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Customer;
import domain.Employee;
import domain.Payment;
import domain.User;

@Dependent
@Transactional
public class CustomerService implements CustomerServiceInterface {

	private EntityManager em;
	
	@Inject
	private UserService userbean;
	
	@Inject
	private EmployeeService empbean;

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
		try {
			Query q = em.createNamedQuery("Customer.findbyId");
			q.setParameter("id", Long.valueOf(id));
			return (Customer) q.getSingleResult();
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public Customer findCustomerbyUsername(String username) throws EJBException {
		Query q = em.createNamedQuery("Customer.findbyUsername");
		q.setParameter("username", username);
		return (Customer) q.getSingleResult();
	}

	@Override
	public List<Customer> adminReadRecords(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.customers order by customernumber OFFSET ? LIMIT ?",
					Customer.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery("SELECT * from classicmodels.customers "
					+ "WHERE concat(customernumber,customername,contactlastname,contactfirstname,city,country,salesrepemployeenumber) LIKE ? "
					+ "order by customernumber OFFSET ? LIMIT ?", Customer.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		try {
			List<Customer> results = q.getResultList();
			return results;
		} catch (Exception ex) {
		}

		return null;
	}
	
	@Override
	public List<Customer> userReadRecords(int currentPage, int recordsPerPage, String keyword, String username) throws EJBException {
		return null;
	}

	@Override
	public List<Customer> staffReadRecords(int currentPage, int recordsPerPage, String keyword, String username) throws EJBException {
		Query q = null;
		Employee employee = empbean.findEmployeebyUsername(username);
		List<Customer> customerList = employee.getCustomers();
		List<Long> customernumbers = new ArrayList<Long>();
		
		for(int i = 0; i < customerList.size(); i++) {
			customernumbers.add(customerList.get(i).getCustomernumber());
		}
		
		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.customers "
					+ "WHERE customernumber IN :customernumbers "
					+ "AND concat(customernumber,customername, contactlastname, contactfirstname, city, country) LIKE :keyword "
					+ "order by customernumber "
					+ "OFFSET :offset LIMIT :limit",
					Customer.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter("customernumbers", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
			q.setParameter("offset", Integer.valueOf(start));
			q.setParameter("limit", Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery("SELECT * from classicmodels.customers "
					+ "WHERE customernumber IN :customernumbers "
					+ "AND concat(customernumber,customername, contactlastname, contactfirstname, city, country) LIKE :keyword "
					+ "order by customernumber OFFSET :offset LIMIT :limit", 
					Customer.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter("customernumbers", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
			q.setParameter("offset", Integer.valueOf(start));
			q.setParameter("limit", Integer.valueOf(recordsPerPage));

		}

		try {
			List<Customer> results = q.getResultList();
			return results;
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public int adminGetNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.customers");

		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.customers "
					+ "WHERE concat(customernumber,customername,contactlastname,contactfirstname,city,country,salesrepemployeenumber) LIKE ?");

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}
	
	@Override
	public int userGetNumberOfRows(String keyword, String username) throws EJBException {
		return 0;
	}

	@Override
	public int staffGetNumberOfRows(String keyword, String username) throws EJBException {
		Query q = null;
		Employee employee = empbean.findEmployeebyUsername(username);
		List<Customer> customerList = employee.getCustomers();
		List<Long> customernumbers = new ArrayList<Long>();
		
		for(int i = 0; i < customerList.size(); i++) {
			customernumbers.add(customerList.get(i).getCustomernumber());
		}
		
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.customers WHERE customernumber IN :customernumbers");
			q.setParameter("customernumbers", customernumbers);
		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.customers "
					+ "WHERE customernumber IN :customernumbers "
					+ "AND concat(customernumber,customername, contactlastname, contactfirstname, city, country) LIKE :keyword");

			q.setParameter("customernumbers", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
		
	}

	@Override
	public boolean updateCustomer(String[] s) throws EJBException {
		Customer customer = findCustomer(s[0]);

		if (!s[12].isBlank()) {
			EmployeeService empService = new EmployeeService(em);

			try {
				Employee employee = empService.findEmployee(s[12]);

				if (employee == null)
					return false;

				customer.setEmployee(employee);
			} catch (Exception ex) {
			}
		}

		customer.setAddressline1(s[1]);
		customer.setAddressline2(s[2].isBlank() ? null : s[2]);

		customer.setCity(s[3]);
		customer.setContactfirstname(s[4]);
		customer.setContactlastname(s[5]);
		customer.setCountry(s[6]);
		customer.setCreditlimit(s[7].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[7]));

		customer.setCustomername(s[8]);
		customer.setPhone(s[9]);
		customer.setPostalcode(s[10]);
		customer.setState(s[11]);

		if (s[12].isBlank())
			customer.setEmployee(null);

		em.merge(customer);
		return true;
	}

	@Override
	public void deleteCustomer(String id) throws EJBException {
		Customer customer = findCustomer(id);
		em.remove(customer);
	}

	@Override
	public boolean addCustomer(String[] s) throws EJBException {
		Customer customer = new Customer();
		User user = userbean.findUser(s[13]);

		if (!s[12].isBlank()) {
			try {
				Employee employee = empbean.findEmployee(s[12]);

				if (employee == null)
					return false;

				customer.setEmployee(employee);
			} catch (Exception ex) {
			}
		}

		customer.setAddressline1(s[1]);
		customer.setAddressline2(s[2].isBlank() ? null : s[2]);

		customer.setCity(s[3]);
		customer.setContactfirstname(s[4]);
		customer.setContactlastname(s[5]);
		customer.setCountry(s[6]);
		customer.setCreditlimit(s[7].isBlank() ? new BigDecimal(0.00) : new BigDecimal(s[7]));

		customer.setCustomername(s[8]);
		customer.setPhone(s[9]);
		customer.setPostalcode(s[10].isBlank() ? null : s[10]);
		customer.setState(s[11].isBlank() ? null : s[11]);
		customer.setUser(user);

		if (s[12].isBlank())
			customer.setEmployee(null);

		em.persist(customer);
		return true;
	}
	
}
