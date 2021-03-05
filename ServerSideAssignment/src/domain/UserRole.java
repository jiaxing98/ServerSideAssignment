package domain;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name="user_roles", schema="classicmodels")
@NamedQuery(name="UserRole.findbyUsername", query="SELECT u FROM UserRole u WHERE u.user =:username")
@NamedQuery(name="UserRole.findbyNameRole", query="SELECT u FROM UserRole u WHERE u.userrolePK.username = :username AND u.userrolePK.role = :role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRolePK userrolePK;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username", insertable=false, updatable=false)
	private User user;
		
	public UserRole() {
	}

	public UserRolePK getId() {
		return this.userrolePK;
	}

	public void setId(UserRolePK id) {
		this.userrolePK = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}