package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products", schema = "classicmodels")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findbyId", query = "SELECT p FROM Product p WHERE p.id = :id")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	@Column(name="productcode")
	private String id;

	private BigDecimal buyprice;

	private BigDecimal msrp;

	private String productdescription;

	private String productname;

	private String productscale;

	private String productvendor;

	private Integer quantityinstock;

	// bi-directional many-to-one association to Orderdetail
	@OneToMany(mappedBy = "product")
	//private List<Orderdetail> orderdetails;

	// bi-directional many-to-one association to Productline
	@ManyToOne
	@JoinColumn(name = "productline", insertable = false, updatable = false)
	private Productline productlineBean;

	public Product() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getBuyprice() {
		return this.buyprice;
	}

	public void setBuyprice(BigDecimal buyprice) {
		this.buyprice = buyprice;
	}

	public BigDecimal getMsrp() {
		return this.msrp;
	}

	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}

	public String getProductdescription() {
		return this.productdescription;
	}

	public void setProductdescription(String productdescription) {
		this.productdescription = productdescription;
	}

	public String getProductname() {
		return this.productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductscale() {
		return this.productscale;
	}

	public void setProductscale(String productscale) {
		this.productscale = productscale;
	}

	public String getProductvendor() {
		return this.productvendor;
	}

	public void setProductvendor(String productvendor) {
		this.productvendor = productvendor;
	}

	public Integer getQuantityinstock() {
		return this.quantityinstock;
	}

	public void setQuantityinstock(Integer quantityinstock) {
		this.quantityinstock = quantityinstock;
	}

//	public List<Orderdetail> getOrderdetails() {
//		return this.orderdetails;
//	}
//
//	public void setOrderdetails(List<Orderdetail> orderdetails) {
//		this.orderdetails = orderdetails;
//	}
//
//	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
//		getOrderdetails().add(orderdetail);
//		orderdetail.setProduct(this);
//
//		return orderdetail;
//	}
//
//	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
//		getOrderdetails().remove(orderdetail);
//		orderdetail.setProduct(null);
//
//		return orderdetail;
//	}

	public Productline getProductlineBean() {
		return this.productlineBean;
	}

	public void setProductlineBean(Productline productlineBean) {
		this.productlineBean = productlineBean;
	}

}
