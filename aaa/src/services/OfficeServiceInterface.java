package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.Office;

public interface OfficeServiceInterface extends CommonServiceInterface<Office> {
	
	public List<Office> getAllOffices() throws EJBException;

	public Office findOffice(String id) throws EJBException;

	public void updateOffice(String[] s) throws EJBException;

	public void deleteOffice(String id) throws EJBException;

	public void addOffice(String[] s) throws EJBException;
}
