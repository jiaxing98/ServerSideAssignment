package domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users", schema="classicmodels")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
@NamedQuery(name="User.findbyUsername", query="SELECT u FROM User u WHERE u.username = :username")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String password;

	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
	
	@OneToOne(mappedBy="user")
	private Employee employees;

	public User() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}