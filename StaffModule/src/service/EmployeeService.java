package service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Employee;
import domain.Office;
import domain.User;


@Dependent
@Transactional
public class EmployeeService implements EmployeeServiceInterface{

	private EntityManager em;
	
	@Inject
	private OfficeService offbean;
	
	@Inject
	private UserService userbean;

	@Inject
	public EmployeeService(@PostGresDatabase EntityManager em) {
		this.em = em;
	}

	public List<Employee> getAllEmployees() throws EJBException {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Employee.findAll").getResultList();
	}

	public List<Employee> readStaff(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		// Write some codes here…
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("select * from classicmodels.employees order by employeenumber OFFSET ? LIMIT ?",
					Employee.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.employees WHERE concat(employeenumber,lastname,firstname,extension,email,officecode,reportsto,jobtitle,username) LIKE? order by id OFFSET ? LIMIT ?",
					Employee.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		}
		List<Employee> results = q.getResultList();
		return results;
	}

	public int getNumberOfRows(String keyword) throws EJBException {
		// Write some codes here…
		Query q = null;
		
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.employees");
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.employees WHERE concat(employeenumber,lastname,firstname,extension,email,officecode,reportsto,jobtitle,username) LIKE ?");
			q.setParameter(1, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	public Employee findEmployee(String id) throws EJBException {
		// Write some codes here…
		Query q = em.createNamedQuery("Employee.findbyId");
		q.setParameter("id", Long.valueOf(id));
		return (Employee) q.getSingleResult();
	}

	public boolean updateEmployee(String[] s) throws EJBException {
		// Write some codes here…
		Employee e = findEmployee(s[0]);
		User user = userbean.findUser(s[8]);
		
		if (!s[5].isBlank()) {
			//OfficeService offService = new OfficeService(em);

			try {
				Office office = offbean.findOffice(s[5]);

				if (office == null)
					return false;
				else
					e.setOffice(office);
	
			} catch (Exception ex) {
			}
		}
		
		e.setLastname(s[1]);
		e.setFirstname(s[2]);
		e.setExtension(s[3]);
		e.setEmail(s[4]);
		e.setReportsto(s[6].isBlank() ? null : s[6]);
		e.setJobtitle(s[7]);
		e.setUser(user);;

		em.merge(e);
		return true;
	}

	public void deleteEmployee(String id) throws EJBException {
		// Write some codes here…
		Employee e = findEmployee(id);
		em.remove(e);
	}

	public boolean addEmployee(String[] s) throws EJBException {
		// Write some codes here…
		
		Employee e = new Employee();
		
		//UserService userservice = new UserService(em);
		User user = userbean.findUser(s[8]);
		
		if (!s[5].isBlank()) {
			//OfficeService offService = new OfficeService(em);

			try {
				Office office = offbean.findOffice(s[5]);

				if (office == null)
					return false;

				e.setOffice(office);
			} catch (Exception ex) {
			}
		}

		e.setLastname(s[1]);
		e.setFirstname(s[2]);
		e.setExtension(s[3]);
		e.setEmail(s[4]);
		e.setReportsto(s[6].isBlank() ? null : s[6]);
		e.setJobtitle(s[7]);
		e.setUser(user);
		
		em.persist(e);
		return true;
	}

}
