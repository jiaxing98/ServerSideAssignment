package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Product;

public interface ProductServiceInterface {

	public List<Product> getAllProduct() throws EJBException;

	public Product findProduct(String id) throws EJBException;

	public List<Product> readProduct(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public int getNumberOfRows(String keyword) throws EJBException;

	public void updateProduct(String[] s) throws EJBException;

	public void deleteProduct(String id) throws EJBException;

	public void addProduct(String[] s) throws EJBException;
}

