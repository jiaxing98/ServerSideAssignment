package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Customer;

public interface CustomerServiceInterface {

	public List<Customer> getAllCustomers() throws EJBException;

	public Customer findCustomer(String id) throws EJBException;

	public List<Customer> readCustomers(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;

	public boolean updateCustomer(String[] s) throws EJBException;

	public void deleteCustomer(String id) throws EJBException;

	public boolean addCustomer(String[] s) throws EJBException;
}
