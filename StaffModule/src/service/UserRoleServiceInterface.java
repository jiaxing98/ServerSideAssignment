package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.UserRole;

public interface UserRoleServiceInterface {

	public List<UserRole> getAllUserRole(String username) throws EJBException;

	public UserRole findUserRole(String username, String role) throws EJBException;

	public void deleteUserRole(String username, String role) throws EJBException;

	public boolean addUserRole(String username, String role) throws EJBException;

}
