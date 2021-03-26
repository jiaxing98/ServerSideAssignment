package service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Order;
import domain.Orderdetail;

@Dependent
@Transactional
public class OrderDetailService implements OrderDetailServiceInterface{
	
	private EntityManager em;

	@Inject
	public OrderDetailService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}
	
	public List<Orderdetail> getAllOrderDetail() throws EJBException {
		// Write some codes here…
		return em.createNamedQuery("Orderdetail.findAll").getResultList();
	}
	
	public List<Integer> getAllOrderNo() throws EJBException {
		// Write some codes here…
		return (List<Integer>) em.createNamedQuery("Orderdetail.findDisONo").getResultList();
	}
	
	public List<Orderdetail> readOrderDetail(int orderNo) throws EJBException {
		// Write some codes here…
		
		Query q = em.createNamedQuery("Orderdetail.findbyId");
		q.setParameter("ordernumber", orderNo);
		
		List<Orderdetail> results = q.getResultList();
		return results;
	}
	
	public Orderdetail findOrderDetail(String oNo, String productCode) throws EJBException {
		// Write some codes here…
		Query q = em.createNamedQuery("Orderdetail.findbyIdnProduct");
		q.setParameter("ordernumber", Integer.valueOf(oNo));
		q.setParameter("productcode", productCode);
		return (Orderdetail) q.getSingleResult();
	}
	
	public void updateOrderDetail(String[] odInfo, String productCode) throws EJBException {
		
		Orderdetail od = findOrderDetail(odInfo[0], productCode);
		BigDecimal price = new BigDecimal(odInfo[3]);
		price.setScale(2);
		
		od.setQuantityordered(Integer.valueOf(odInfo[2]));
		od.setPriceeach(price);
		od.setOrderlinenumber(Integer.valueOf(odInfo[4]));
		em.merge(od);
		
	}
	
	public void deleteOrderDetail(String id, String productCode) throws EJBException {
		// Write some codes here…
		Orderdetail od = findOrderDetail(id, productCode);
		em.remove(od);
	}
	

}