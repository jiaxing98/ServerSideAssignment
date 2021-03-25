package services;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import services.PostGresDatabase;
import domain.Order;

@Dependent
@Transactional
public class OrderServices implements OrderServicesInterface{
	
	private EntityManager em;

	@Inject
	public OrderServices(@PostGresDatabase EntityManager em) {
		this.em = em;
	}

	public List<Order> getAllOrders() throws EJBException {
		// Write some codes here…
		return em.createNamedQuery("Order.findAll").getResultList();
	}

	public List<Order> readOrder(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		// Write some codes here…
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("select * from classicmodels.orders order by ordernumber OFFSET ? LIMIT ?",
					Order.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.orders WHERE concat(ordernumber, customernumber, status) LIKE ? order by ordernumber OFFSET ? LIMIT ?",
					Order.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		}
		List<Order> results = q.getResultList();
		return results;
	}

	public int getNumberOfOrder(String keyword) throws EJBException {
		// Write some codes here…
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.orders");
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.orders WHERE concat(ordernumber, customernumber, status) LIKE ?");
			q.setParameter(1, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	public Order findOrder(String oNo) throws EJBException {
		// Write some codes here…
		Query q = em.createNamedQuery("Order.findbyId");
		q.setParameter("ordernumber", Integer.valueOf(oNo));
		return (Order) q.getSingleResult();
	}

	public void updateOrder(String[] o) throws EJBException {
		// Write some codes here…

		Order order = findOrder(o[0]);
		
		order.setRequireddate(o[2]);
		order.setShippeddate(o[3]);
		order.setStatus(o[4]);
		order.setComments(o[5]);
		em.merge(order);
	}

	public void deleteOrder(String id) throws EJBException {
		// Write some codes here…
		Order order = findOrder(id);
		em.remove(order);
	}

	/*
	 * public void addOrder(String[] o) throws EJBException { // Write some codes
	 * here… Date dob = null; Date hd = null; try { dob = new
	 * SimpleDateFormat("yyyy-MM-dd").parse(s[4]); hd = new
	 * SimpleDateFormat("yyyy-MM-dd").parse(s[5]); } catch (Exception ex) { } Order
	 * o = new Order(); java.sql.Date DOB = new java.sql.Date(dob.getTime());
	 * java.sql.Date HD = new java.sql.Date(hd.getTime()); e.setFirstName(s[1]);
	 * e.setLastName(s[2]); e.setGender(s[3]); e.setBirthDate(DOB);
	 * e.setHireDate(HD); em.persist(o); }
	 */

}
