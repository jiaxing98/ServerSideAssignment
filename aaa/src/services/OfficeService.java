package services;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Office;

@Dependent
@Transactional
public class OfficeService implements OfficeServiceInterface {

	private EntityManager em;

	@Inject
	public OfficeService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Office> getAllOffices() throws EJBException {
		return em.createNamedQuery("Office.findAll").getResultList();
	}

	@Override
	public List<Office> adminReadRecords(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("select * from classicmodels.offices order by officecode OFFSET ? LIMIT ?",
					Office.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.offices WHERE concat(officecode,city,phone,addressline1,addressline2,state,country,postalcode,territory) LIKE ? order by officecode OFFSET ? LIMIT ?",
					Office.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		}
		List<Office> results = q.getResultList();
		return results;
	}

	@Override
	public int adminGetNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.offices");
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.offices WHERE concat(officecode,city,phone,addressline1) LIKE ?");
			q.setParameter(1, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public Office findOffice(String id) throws EJBException {
		Query q = em.createNamedQuery("Office.findbyId");
		q.setParameter("id", Long.valueOf(id));
		return (Office) q.getSingleResult();
	}

	@Override
	public void updateOffice(String[] s) throws EJBException {
		Office o = findOffice(s[0]);

		o.setCity(s[1]);
		o.setPhone(s[2]);
		o.setAddressline1(s[3]);
		o.setAddressline2(s[4].isBlank() ? null : s[4]);
		o.setState(s[5].isBlank() ? null : s[5]);
		o.setCountry(s[6]);
		o.setPostalcode(s[7]);
		o.setTerritory(s[8]);

		em.merge(o);
	}

	@Override
	public void deleteOffice(String id) throws EJBException {
		Office o = findOffice(id);
		em.remove(o);
	}

	@Override
	public void addOffice(String[] s) throws EJBException {

		Office o = new Office();

		o.setCity(s[1]);
		o.setPhone(s[2]);
		o.setAddressline1(s[3]);
		o.setAddressline2(s[4].isBlank() ? null : s[4]);
		o.setState(s[5].isBlank() ? null : s[5]);
		o.setCountry(s[6]);
		o.setPostalcode(s[7]);
		o.setTerritory(s[8]);

		em.persist(o);
	}

	@Override
	public List<Office> userReadRecords(int currentPage, int recordsPerPage, String keyword, String username)
			throws EJBException {
		return null;
	}

	@Override
	public List<Office> staffReadRecords(int currentPage, int recordsPerPage, String keyword, String username)
			throws EJBException {
		return null;
	}

	@Override
	public int userGetNumberOfRows(String keyword, String username) throws EJBException {
		return 0;
	}

	@Override
	public int staffGetNumberOfRows(String keyword, String username) throws EJBException {
		return 0;
	}

}