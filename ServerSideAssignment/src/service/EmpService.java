package service;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import domain.Customer;
import domain.Employee;

public class EmpService implements EmpServiceInterface{

	private EntityManager em;
	
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
	public List<Employee> readEmployees(int currentPage, int recordsPerPage, String keyword) throws EJBException {
		Query q = null;

		if (keyword.isEmpty()) {

			q = em.createNativeQuery("select e.* from classicmodels.employees e "
					+ "JOIN classicmodels.offices o "
					+ "ON e.officecode = o.officecode "
					+ "order by employeenumber OFFSET ? LIMIT ?",
					Customer.class);

			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, Integer.valueOf(start));
			q.setParameter(2, Integer.valueOf(recordsPerPage));
		} else {
			q = em.createNativeQuery(
					"SELECT e.* from classicmodels.employees e "
					+ "JOIN classicmodels.offices o "
					+ "ON e.officecode = o.officecode "
					+ "WHERE concat(employeenumber,lastname,firstname,jobtitle) LIKE ? "
					+ "order by employeenumber OFFSET ? LIMIT ?",
					Customer.class);
			int start = currentPage * recordsPerPage - recordsPerPage;
			q.setParameter(1, "%" + keyword + "%");
			q.setParameter(2, Integer.valueOf(start));
			q.setParameter(3, Integer.valueOf(recordsPerPage));

		}

		List<Employee> results = q.getResultList();
		return results;
	}

	@Override
	public int getNumberOfRows(String keyword) throws EJBException {
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
	public boolean updateEmployee(String[] s) throws EJBException {
		//Office office = findOffice(s[12]);
		//if is null, return false
		
		Employee employee = findEmployee(s[0]);

		employee.setEmail(s[1]);
		employee.setExtension(s[2]);
		employee.setFirstname(s[3]);
		employee.setJobtitle(s[4]);
		employee.setLastname(s[5]);
		employee.setReportsto(s[6]);
		//employee.setOffice(office);

		em.merge(employee);
		return true;
	}

	@Override
	public void deleteEmployee(String id) throws EJBException {
		Employee employee = findEmployee(id);
		em.remove(employee);
	}

	@Override
	public boolean addEmployee(String[] s) throws EJBException {
		//Office office = findOffice(s[12]);
		//if is null, return false
		
		Employee employee = new Employee();

		employee.setEmail(s[1]);
		employee.setExtension(s[2]);
		employee.setFirstname(s[3]);
		employee.setJobtitle(s[4]);
		employee.setLastname(s[5]);
		employee.setReportsto(s[6]);
		//employee.setOffice(office);
		
		em.persist(employee);
		return true;
	}

}
