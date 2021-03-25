package services;

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
	
	public List<Orderdetail> readOrderDetail(int orderNo) throws EJBException {
		// Write some codes here…
		Query q = null;
		q = em.createNativeQuery("select * from classicmodels.orderdetails where ordernumber= ? order by productcode",
				Order.class);
		q.setParameter(1, orderNo);
		
		List<Orderdetail> results = q.getResultList();
		return results;
	}
	
	public Orderdetail findOrderDetail(String oNo) throws EJBException {
		// Write some codes here…
		Query q = em.createNamedQuery("OrderDetail.findbyId");
		q.setParameter("ordernumber", Integer.valueOf(oNo));
		return (Orderdetail) q.getSingleResult();
	}
	
	public void updateOrderDetail(String[] odInfo) throws EJBException {
		
		Orderdetail od = findOrderDetail(odInfo[0]);
		BigDecimal price = new BigDecimal(odInfo[3]);
		price.setScale(2);
		
		od.setQuantityordered(Integer.valueOf(odInfo[2]));
		od.setPriceeach(price);
		od.setOrderlinenumber(Integer.valueOf(odInfo[4]));
		em.merge(od);
		
	}
	
	public void deleteOrderDetail(String id) throws EJBException {
		// Write some codes here…
		Orderdetail od = findOrderDetail(id);
		em.remove(od);
	}
	

}
