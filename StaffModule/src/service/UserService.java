package service;

import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.User;

@Dependent
@Transactional
public class UserService implements UserServiceInterface {
	
	private EntityManager em;

	@Inject
    public UserService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }

	
	@Override
	public List<User> getAllUsers() throws EJBException {
		return em.createNamedQuery("User.findAll").getResultList();
	}

	@Override
	public User findUser(String username) throws EJBException {
		try {
			Query q = em.createNamedQuery("User.findbyUsername");
			q.setParameter("username", username);
			return (User) q.getSingleResult();
		}
		catch (Exception ex) {
		}
		
		return null;
	}

	@Override
	public boolean loginUser(String username, String password) throws EJBException {
		User user = findUser(username);
		
		if(user == null) {
			return false;
		}
		else {
			if(user.getPassword().equals(password)) {
				return true;
			}
			else
				return false;
		}
	}
	
	@Override
	public void updateUser(String[] s, String action, String newInfo) throws EJBException {
		User user = findUser(s[0]);
		
		if(action.equals("CHANGEUN")) {
			user.setUsername(newInfo);
		}
		else if(action.equals("CHANGEPW")) {
			user.setPassword(newInfo);
		}
		
		em.merge(user);
	}

	@Override
	public void deleteUser(String username) throws EJBException {
		User user = findUser(username);
		em.remove(user);
	}

	@Override
	public boolean addUser(String username, String password) throws EJBException {
		User user = findUser(username);
		
		if(user != null)
			return false;
		
		User newUser = new User();
		
		newUser.setUsername(username);
		newUser.setPassword(password);
		em.persist(newUser);
		return true;
	}
	

}
