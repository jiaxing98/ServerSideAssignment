package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Employee;

public interface EmpServiceInterface {
	
	public List<Employee> getAllEmployees() throws EJBException;

	public Employee findEmployee(String id) throws EJBException;

	public List<Employee> readEmployees(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;

	public boolean updateEmployee(String[] s) throws EJBException;

	public void deleteEmployee(String id) throws EJBException;

	public boolean addEmployee(String[] s) throws EJBException;
}
