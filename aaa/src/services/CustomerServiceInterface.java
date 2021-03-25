package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.Customer;
import domain.Employee;

public interface CustomerServiceInterface extends CommonServiceInterface<Customer> {

	public List<Customer> getAllCustomers() throws EJBException;

	public Customer findCustomer(String id) throws EJBException;

	//public Customer findSalesRep(String empno) throws EJBException;

	public Customer findCustomerbyUsername(String username) throws EJBException;

	public boolean updateCustomer(String[] s) throws EJBException;

	public void deleteCustomer(String id) throws EJBException;

	public boolean addCustomer(String[] s) throws EJBException;

	// public boolean removeCustomer(String[] s) throws EJBException;

	public boolean removeCustomer(String id, String employee) throws EJBException;
}
