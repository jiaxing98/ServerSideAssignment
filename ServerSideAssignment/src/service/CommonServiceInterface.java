package service;

import java.util.List;

import javax.ejb.EJBException;

import domain.Payment;

public interface CommonServiceInterface<T> {
	public List<T> adminReadRecords(int currentPage, int recordsPerPage, String keyword) throws EJBException;

	public List<T> userReadRecords(int currentPage, int recordsPerPage, String keyword, String username)
			throws EJBException;

	public List<T> staffReadRecords(int currentPage, int recordsPerPage, String keyword, String username)
			throws EJBException;

	public int adminGetNumberOfRows(String keyword) throws EJBException;

	public int userGetNumberOfRows(String keyword, String username) throws EJBException;

	public int staffGetNumberOfRows(String keyword, String username) throws EJBException;
}
