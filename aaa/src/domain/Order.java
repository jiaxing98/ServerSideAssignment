package domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders", schema="classicmodels")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
@NamedQuery(name = "Order.findbyId", query = "SELECT o FROM Order o WHERE o.ordernumber = :ordernumber")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ordernumber;

	private String comments;

	private Integer customernumber;

	private String orderdate;

	private String requireddate;

	private String shippeddate;

	private String status;

	public Order() {
	}

	public Integer getOrdernumber() {
		return this.ordernumber;
	}

	public void setOrdernumber(Integer ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCustomernumber() {
		return this.customernumber;
	}

	public void setCustomernumber(Integer customernumber) {
		this.customernumber = customernumber;
	}

	public String getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public String getRequireddate() {
		return this.requireddate;
	}

	public void setRequireddate(String requireddate) {
		this.requireddate = requireddate;
	}

	public String getShippeddate() {
		return this.shippeddate;
	}

	public void setShippeddate(String shippeddate) {
		this.shippeddate = shippeddate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}