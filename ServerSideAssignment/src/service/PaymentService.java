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

import domain.Payment;
import domain.PaymentPK;

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
