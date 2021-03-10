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
import domain.PaymentPK;

@Dependent
@Transactional
public class PaymentService implements PaymentServiceInterface {

	@Inject
	private CustomerService customerbean;
	
	@Inject
	private EmpService empbean;
	
	private EntityManager em;

	@Inject
    public PaymentService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }
	
	@Override
	public List<Payment> getAllPayment() throws EJBException {
		return em.createNamedQuery("Payment.findAll").getResultList();
	}

	@Override
	public List<Payment> findCustomer(String customernumber) throws EJBException {
		try {
			Query q = em.createNamedQuery("Payment.findbycustomernumber");
			q.setParameter("customernumber", Integer.parseInt(customernumber));
			return (List<Payment>) q.getResultList();
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public List<Payment> findPaymentMethod(String paymentmethod) throws EJBException {
		try {
			Query q = em.createNamedQuery("Payment.findbypaymentmethod");
			q.setParameter("paymentmethod", paymentmethod);
			return (List<Payment>) q.getResultList();
		} catch (Exception ex) {
		}

		return null;
	}

	@Override
	public Payment findPayment(PaymentPK paymentPK) throws EJBException {
		int customernumber = paymentPK.getCustomernumber();
		String checknumber = paymentPK.getChecknumber();
		
		try {
			Query q = em.createNamedQuery("Payment.findbypaymentPK");
			q.setParameter("customernumber", customernumber);
			q.setParameter("checknumber", checknumber);
			return (Payment) q.getSingleResult();
		} catch (Exception ex) {
		}
		
		return null;
	}

	@Override
	public List<Payment> adminReadRecords(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {
			
			q = em.createNativeQuery("select * from classicmodels.payments order by customernumber OFFSET ? LIMIT ?",
					Payment.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.payments "
					+ "WHERE concat(customernumber,checknumber,paymentmethod) LIKE ? "
					+ "order by customernumber OFFSET ? LIMIT ?",
					Payment.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		List<Payment> results = q.getResultList();
		return results;

	}
	
	@Override
	public List<Payment> userReadRecords(int currentPage, int recordsPerPage, String keyword, String username) throws EJBException {
		Query q = null;
		Customer customer = customerbean.findCustomerbyUsername(username);
			
		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.payments WHERE customernumber = ? OFFSET ? LIMIT ?",
					Payment.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, customer.getCustomernumber());
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.payments "
					+ "WHERE customernumber = ? "
					+ "AND concat(checknumber,paymentmethod) LIKE ? "
					+ "OFFSET ? LIMIT ?",
					Payment.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, customer.getCustomernumber());
			q.setParameter(2, "%" + keyword + "%");
			q.setParameter(3, Integer.valueOf(start));
			q.setParameter(4, Integer.valueOf(recordsPerPage));

		}

		List<Payment> results = q.getResultList();
		return results;

	}
	
	@Override
	public List<Payment> staffReadRecords(int currentPage, int recordsPerPage, String keyword, String username) throws EJBException {
		Query q = null;
		Employee employee = empbean.findEmployeebyUsername(username);
		List<Customer> customerList = employee.getCustomers();
		List<Long> customernumbers = new ArrayList<Long>();
		
		for(int i = 0; i < customerList.size(); i++) {
			customernumbers.add(customerList.get(i).getCustomernumber());
		}
			
		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.payments "
					+ "WHERE customernumber IN :list "
					+ "AND concat(checknumber,paymentmethod) LIKE :keyword "
					+ "order by customernumber "
					+ "OFFSET :offset LIMIT :limit",
					Payment.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter("list", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
			q.setParameter("offset", Integer.valueOf(start));
			q.setParameter("limit", Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.payments "
					+ "WHERE customernumber IN :list "
					+ "AND concat(checknumber,paymentmethod) LIKE :keyword "
					+ "order by customernumber "
					+ "OFFSET :offset LIMIT :limit",
					Payment.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter("list", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
			q.setParameter("offset", Integer.valueOf(start));
			q.setParameter("limit", Integer.valueOf(recordsPerPage));

		}

		List<Payment> results = q.getResultList();
		return results;

	}

	@Override
	public int adminGetNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.payments");

		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.payments "
					+ "WHERE concat(customernumber,checknumber,paymentmethod) LIKE ?");	//search condition

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}
	
	@Override
	public int userGetNumberOfRows(String keyword, String username) throws EJBException {
		Query q = null;
		Customer customer = customerbean.findCustomerbyUsername(username);
		
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.payments WHERE customernumber = ?");
			q.setParameter(1, customer.getCustomernumber());
		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.payments "
					+ "WHERE customernumber = ?"
					+ "AND concat(checknumber,paymentmethod) LIKE ?");	//search condition

			q.setParameter(1, customer.getCustomernumber());
			q.setParameter(2, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
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
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.payments WHERE customernumber IN :list");
			q.setParameter("list", customernumbers);
		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.payments "
					+ "WHERE customernumber IN :list "
					+ "AND concat(checknumber,paymentmethod) LIKE :keyword");	//search condition

			q.setParameter("list", customernumbers);
			q.setParameter("keyword", "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public void deletePayment(PaymentPK paymentPK) throws EJBException {
		Payment payment = findPayment(paymentPK);
		em.remove(payment);
	}

	@Override
	public boolean addPayment(PaymentPK paymentPK, String[] s) throws EJBException {
		Payment payment = findPayment(paymentPK);
		
		if(payment != null)
			return false;
		
		Payment newPayment = new Payment();
		newPayment.setId(paymentPK);
		newPayment.setAmount(new BigDecimal(s[0]));
		newPayment.setPaymentdate(s[1]);
		newPayment.setPaymentmethod(s[2]);

		em.persist(newPayment);
		return true;
	}

}
