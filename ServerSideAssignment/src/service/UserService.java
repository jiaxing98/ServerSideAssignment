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
public class UserService implements UserServiceInterface{

	private EntityManager em;

	@Inject
    public UserService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }

	
	@Override
	public List<User> getAllUsers() throws EJBException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Customer.findAll").getResultList();
	}

	@Override
	public User findUser(String id) throws EJBException {
		// TODO Auto-generated method stub
		Query q = em.createNamedQuery("user.findbyUsername");
		return (User) q.getSingleResult();
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		User user = findUser(username);
		em.remove(user);
	}

	@Override
	public void addUser(String[] s) throws EJBException {
		User user = new User();
		
		user.setUsername(s[0]);
		user.setPassword(s[1]);
		em.persist(user);
	}
	
}
