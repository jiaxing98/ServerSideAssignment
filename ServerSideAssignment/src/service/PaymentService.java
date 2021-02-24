package service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Payment;

@Dependent
@Transactional
public class PaymentService implements PaymentServiceInterface {

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
		Query q = em.createNamedQuery("Payment.findbycustomernumber");
		q.setParameter("customernumber", Integer.parseInt(customernumber));
		return (List<Payment>) q.getResultList();
	}

	@Override
	public List<Payment> findPaymentMethod(String paymentmethod) throws EJBException {
		Query q = em.createNamedQuery("Payment.findbypaymentmethod");
		q.setParameter("paymentmethod", paymentmethod);
		return (List<Payment>) q.getResultList();
	}

	@Override
	public Payment findCheckNumber(String checknumber) throws EJBException {
		Query q = em.createNamedQuery("Payment.findbyCheckNumber");
		q.setParameter("checknumber", checknumber);
		return (Payment) q.getSingleResult();
	}

	@Override
	public List<Payment> readPayment(int currentPage, int recordsPerPage, String keyword) throws EJBException {
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
	public int getNumberOfRows(String keyword) throws EJBException {
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

}
