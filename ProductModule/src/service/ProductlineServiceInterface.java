package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Productline;

public interface ProductlineServiceInterface {
	
	public List<Productline> getAllProductline() throws EJBException;

	public Productline findProductline(String productline) throws EJBException;

	public List<Productline> readProductlines(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;

	public void updateProductline(String[] s) throws EJBException;

	public void deleteProductline(String productline) throws EJBException;

	public void addProductline(String[] s) throws EJBException;

}
