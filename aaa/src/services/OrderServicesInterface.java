package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.Order;

public interface OrderServicesInterface {
	
	public List<Order> getAllOrders() throws EJBException;
	public Order findOrder(String oNo) throws EJBException;
	public List<Order> readOrder(int currentPage, int recordsPerPage, String keyword) throws EJBException;
	public int getNumberOfOrder(String keyword) throws EJBException;
	public void updateOrder(String[] s) throws EJBException;
	public void deleteOrder(String id) throws EJBException;
	//public void addOrder(String[] s) throws EJBException;

}
