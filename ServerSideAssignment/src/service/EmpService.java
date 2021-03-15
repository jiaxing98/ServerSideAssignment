package service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import domain.Customer;
import domain.Employee;
import domain.Office;
import domain.User;


@Dependent
@Transactional
public class EmpService implements EmpServiceInterface{

	private EntityManager em;
	
	@Inject
	private OfficeService offbean;
	
	@Inject
	private UserService userbean;
	
	@Inject
    public EmpService(@PostGresDatabase EntityManager em) {
    	this.em = em;
    }
	
	@Override
	public List<Employee> getAllEmployees() throws EJBException {
		return em.createNamedQuery("Employee.findAll").getResultList();
	}

	@Override
	public Employee findEmployee(String id) throws EJBException {
		Query q = em.createNamedQuery("Employee.findbyId");
		q.setParameter("id", Long.valueOf(id));
		return (Employee) q.getSingleResult();
	}
	
	@Override
	public Employee findEmployeebyUsername(String username) throws EJBException {
		Query q = em.createNamedQuery("Employee.findbyUsername");
		q.setParameter("username", username);
		return (Employee) q.getSingleResult();
	}

	@Override
	public List<Employee> adminReadRecords(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.employees "
					+ "order by employeenumber OFFSET ? LIMIT ?",
					Employee.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.employees "
					+ "WHERE concat(employeenumber,lastname,firstname,jobtitle) LIKE ? "
					+ "order by employeenumber OFFSET ? LIMIT ?",
					Employee.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		List<Employee> results = q.getResultList();
		return results;
	}
	
	@Override
	public List<Employee> staffReadRecords(int currentPage, int recordsPerPage, String keyword, String username) throws EJBException {
		Query q = null;
		Employee employee = findEmployeebyUsername(username);
			
		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select * from classicmodels.employees WHERE employeenumber = ? OFFSET ? LIMIT ?",
					Employee.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, employee.getEmployeenumber());
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT * from classicmodels.employees "
					+ "WHERE employeenumber = ? "
					+ "AND concat(employeenumber,lastname,firstname,extension,email,officecode,reportsto,jobtitle,username) LIKE ? "
					+ "OFFSET ? LIMIT ?",
					Employee.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, employee.getEmployeenumber());
			q.setParameter(2, "%" + keyword + "%");
			q.setParameter(3, Integer.valueOf(start));
			q.setParameter(4, Integer.valueOf(recordsPerPage));

		}

		List<Employee> results = q.getResultList();
		return results;

	}

	@Override
	public int adminGetNumberOfRows(String keyword) throws EJBException {
		Query q = null;
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.employees");

		} else {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow from classicmodels.employees "
					+ "WHERE concat(employeenumber,lastname,firstname,jobtitle) LIKE ?");

			q.setParameter(1, "%" + keyword + "%");
		}

		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}
	
	@Override
	public int staffGetNumberOfRows(String keyword, String username) throws EJBException {
		Query q = null;
		Employee employee = findEmployeebyUsername(username);
		
		if (keyword.isEmpty()) {
			q = em.createNativeQuery("SELECT COUNT(*) AS totalrow FROM classicmodels.employees WHERE employeenumber = ?");
			q.setParameter(1, employee.getEmployeenumber());
		} else {
			q = em.createNativeQuery(
					"SELECT COUNT(*) AS totalrow from classicmodels.employees WHERE concat(employeenumber,lastname,firstname,extension,email,officecode,reportsto,jobtitle,username) LIKE ?");
			q.setParameter(1, employee.getEmployeenumber());
			q.setParameter(2, "%" + keyword + "%");
		}
		BigInteger results = (BigInteger) q.getSingleResult();
		int i = results.intValue();
		return i;
	}

	@Override
	public boolean updateEmployee(String[] s) throws EJBException {
		Employee e = findEmployee(s[0]);
		User user = userbean.findUser(s[8]);
		
		if (!s[5].isBlank()) {
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
		e.setUser(user);
		
		em.merge(e);
		return true;
	}

	@Override
	public void deleteEmployee(String id) throws EJBException {
		Employee employee = findEmployee(id);
		em.remove(employee);
	}

	@Override
	public boolean addEmployee(String[] s) throws EJBException {
		
		Employee e = new Employee();
		
		User user = userbean.findUser(s[8]);
		
		if (!s[5].isBlank()) {
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

	@Override
	public List<Employee> userReadRecords(int currentPage, int recordsPerPage, String keyword, String username)
			throws EJBException {
		return null;
	}

	@Override
	public int userGetNumberOfRows(String keyword, String username) throws EJBException {
		return 0;
	}

}
