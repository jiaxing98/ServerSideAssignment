package services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import services.PostGresDatabase;
import utilities.PKgenerator;
import domain.Customer;
import domain.Order;
import domain.Orderdetail;
import domain.OrderdetailPK;
import domain.Product;
import domain.User;

@Dependent
@Transactional
public class OrderServices implements OrderServicesInterface {

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

	@Override
	public void addOrder(String[] o, List<String[]> oDetails, List<Product> product, Customer cus, List<String> odNo) throws EJBException {
		Order order = new Order();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime today = LocalDateTime.now();
		String orderDate = dtf.format(today);
		
		order.setOrderdate(orderDate);	
		order.setRequireddate(orderDate);
		order.setStatus("In Process");
		order.setComments(o[5]);
		int cusNum = Math.toIntExact(cus.getCustomernumber());
		order.setCustomernumber(cusNum);
		order.setOrdernumber(Integer.parseInt(PKgenerator.getAlphaNumericString(odNo)));
		em.persist(order);
		
		for(int i = 0; i < oDetails.size(); i++) {
			String[] temp = oDetails.get(i);
			int buyQty = Integer.parseInt(temp[0]);
			BigDecimal prEach = new BigDecimal(Double.parseDouble(temp[1]));
			int oLineNo = Integer.parseInt(temp[2]);
			
			OrderdetailPK odpk = new OrderdetailPK();
			Orderdetail od = new Orderdetail();
			od.setOrder(order);
			od.setProduct(product.get(i));
			od.setQuantityordered(buyQty);
			od.setPriceeach(prEach);
			od.setOrderlinenumber(oLineNo);
			odpk.setOrdernumber(order.getOrdernumber());
			odpk.setProductcode(product.get(i).getProductcode());
			od.setId(odpk);
			em.persist(od);
		}	
			
	}

}
