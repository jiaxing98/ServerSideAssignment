package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.Productline;

public interface ProductlineServiceInterface {
	
	public List<Productline> getAllProductline() throws EJBException;
	//public int getNumberOfProducts(String keyword) throws EJBException;

}
