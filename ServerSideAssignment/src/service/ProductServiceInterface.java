package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Product;

public interface ProductServiceInterface {
	
	public List<Product> getAllProduct() throws EJBException;

	public Product findProduct(String productcode) throws EJBException;
	
	public String findProductCode(String productName) throws EJBException;

	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword, String pdlSelected) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;
	
	public boolean updateProduct(String[] s) throws EJBException;

	public void deleteProduct(String productcode) throws EJBException;

	public boolean addProduct(String[] s) throws EJBException;
}
