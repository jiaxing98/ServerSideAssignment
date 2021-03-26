package services;

import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.User;
import domain.UserRole;
import domain.UserRolePK;

@Dependent
@Transactional
public class UserRoleService implements UserRoleServiceInterface{

	private EntityManager em;
	
	@Inject
    public UserRoleService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }
	
	@Override
	public List<UserRole> getAllUserRole(String username) throws EJBException {
		Query q = em.createNamedQuery("UserRole.findbyUsername");
		q.setParameter("username", username);
		return q.getResultList();
	}

	@Override
	public UserRole findUserRole(String username, String role) throws EJBException {
		try {
			Query q = em.createNamedQuery("UserRole.findbyNameRole");
			q.setParameter("user", username);
			q.setParameter("role", role);
			return (UserRole) q.getSingleResult();
		} catch (Exception ex) {
			
		}

		return null;
	}
	
	@Override
	public void deleteUserRole(String username, String role) throws EJBException {
		UserRole user_role = findUserRole(username, role);
		em.remove(user_role);
	}

	@Override
	public boolean addUserRole(String username, String role) throws EJBException {
		UserService userservice = new UserService(em);
		User user = userservice.findUser(username);
		UserRole user_role = findUserRole(username, role);
		
		
		if(user_role != null)
			return false;
		
		UserRolePK newRolePK = new UserRolePK();
		newRolePK.setUsername(username);
		newRolePK.setRole(role);
		
		UserRole newRole = new UserRole();
		newRole.setId(newRolePK);
		newRole.setUser(user);
		em.persist(newRole);
		return true;
	}



}
