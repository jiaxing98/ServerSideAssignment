package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.Product;

public interface ProductServiceInterface {
	
	public List<Product> getAllProduct() throws EJBException;
	public int getNumberOfProduct(String keyword) throws EJBException;
	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword, String pdlSelected) throws EJBException;

}
