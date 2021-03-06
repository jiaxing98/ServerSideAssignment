package domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name = "employees", schema = "classicmodels")
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
@NamedQuery(name = "Employee.findbyId", query = "SELECT e FROM Employee e WHERE e.id = :id")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Id
	@Column(name = "employeenumber")
	private Long id;

	private String email;

	private String extension;

	private String firstname;

	private String jobtitle;

	private String lastname;

	private String reportsto;

	// private String username;

	// bi-directional many-to-one association to Office
	@ManyToOne
	@JoinColumn(name = "officecode", insertable = false, updatable = false)
	private Office office;

	@OneToOne
	@JoinColumn(name = "username")
	private User user;

	public Employee() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Office getOffice() {
		return this.office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

}