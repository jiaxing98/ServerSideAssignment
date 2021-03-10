package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Employee;

public interface EmpServiceInterface extends CommonServiceInterface<Employee> {
	
	public List<Employee> getAllEmployees() throws EJBException;

	public Employee findEmployee(String id) throws EJBException;
	
	public Employee findEmployeebyUsername(String username) throws EJBException;

	public boolean updateEmployee(String[] s) throws EJBException;

	public void deleteEmployee(String id) throws EJBException;

	public boolean addEmployee(String[] s) throws EJBException;
}
