package services;

import java.util.List;

import javax.ejb.EJBException;

import domain.User;

public interface UserServiceInterface {
	
	public List<User> getAllUsers() throws EJBException;

	public User findUser(String id) throws EJBException;

	public boolean loginUser(String username, String password) throws EJBException;
	
	public void updateUser(String[] s, String action, String newInfo) throws EJBException;

	public void deleteUser(String id) throws EJBException;

	public boolean addUser(String username, String password) throws EJBException;
	
}
