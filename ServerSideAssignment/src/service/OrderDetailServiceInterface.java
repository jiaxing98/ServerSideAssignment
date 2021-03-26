package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Order;
import domain.Orderdetail;

public interface OrderDetailServiceInterface {

	public List<Orderdetail> getAllOrderDetail() throws EJBException;

	public List<Integer> getAllOrderNo() throws EJBException;

	public List<Orderdetail> readOrderDetail(int orderNo) throws EJBException;

	public Orderdetail findOrderDetail(String oNo, String productCode) throws EJBException;

	public void updateOrderDetail(String[] odInfo, String productCode) throws EJBException;

	public void deleteOrderDetail(String id, String productCode) throws EJBException;

}