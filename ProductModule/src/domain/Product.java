package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "products", schema = "classicmodels")
//@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findbyId", query = "SELECT p FROM Product p WHERE p.productcode = :productcode")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	//@Column(name="productcode")
	private String productcode;
	
	private String productname;

	private BigDecimal buyprice;

	private BigDecimal msrp;

	private String productdescription;

	private String productscale;

	private String productvendor;

	private Short quantityinstock;

	
	@OneToMany(mappedBy = "product")
	//private List<Orderdetail> orderdetails;


	@ManyToOne
	@JoinColumn(name = "productline", insertable = true, updatable = true)
	private Productline productlineBean;

	public Product() {
	}

	public String getProductcode() {
		return this.productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
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

	public Short getQuantityinstock() {
		return this.quantityinstock;
	}

	public void setQuantityinstock(Short quantityinstock) {
		this.quantityinstock = quantityinstock;
	}


	public Productline getProductlineBean() {
		return this.productlineBean;
	}

	public void setProductlineBean(Productline productlineBean) {
		this.productlineBean = productlineBean;
	}
	

	

}
