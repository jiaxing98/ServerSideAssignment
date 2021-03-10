package domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees", schema="classicmodels")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
@NamedQuery(name="Employee.findbyId", query="SELECT e FROM Employee e WHERE e.id = :id")
@NamedQuery(name="Employee.findbyUsername", query="SELECT e FROM Employee e WHERE e.user.username = :username")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="employeenumber")
	private Long id;

	private String email;

	private String extension;

	private String firstname;

	private String jobtitle;

	private String lastname;

	private String reportsto;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="employee")
	private List<Customer> customers;

	//bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name="officecode", insertable=true, updatable=true)
	private Office office;
	
	@OneToOne
	@JoinColumn(name="username")
	private User user;

	public Employee() {
	}

	public Long getEmployeenumber() {
		return this.id;
	}

	public void setEmployeenumber(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getJobtitle() {
		return this.jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getReportsto() {
		return this.reportsto;
	}

	public void setReportsto(String reportsto) {
		this.reportsto = reportsto;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setEmployee(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setEmployee(null);

		return customer;
	}

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}