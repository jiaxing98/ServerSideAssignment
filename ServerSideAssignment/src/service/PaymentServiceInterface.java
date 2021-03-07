package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Payment;
import domain.PaymentPK;

public interface PaymentServiceInterface extends CommonServiceInterface<Payment> {
	
	public List<Payment> getAllPayment() throws EJBException;
	
	public Payment findPayment(PaymentPK paymentPK) throws EJBException;

	public List<Payment> findCustomer(String customernumber) throws EJBException;
	
	public List<Payment> findPaymentMethod(String paymentmethod) throws EJBException;
	
	public void deletePayment(PaymentPK paymentPK) throws EJBException;

	public boolean addPayment(PaymentPK paymentPK, String[] s) throws EJBException;

}
