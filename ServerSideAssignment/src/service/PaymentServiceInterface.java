package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Payment;
import domain.PaymentPK;

public interface PaymentServiceInterface {
	
	public List<Payment> getAllPayment() throws EJBException;
	
	public Payment findPayment(PaymentPK paymentPK) throws EJBException;

	public List<Payment> findCustomer(String customernumber) throws EJBException;
	
	public List<Payment> findPaymentMethod(String paymentmethod) throws EJBException;

	public List<Payment> readPayment(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;
	
	public void deletePayment(PaymentPK paymentPK) throws EJBException;

	public boolean addPayment(PaymentPK paymentPK, String[] s) throws EJBException;

}
