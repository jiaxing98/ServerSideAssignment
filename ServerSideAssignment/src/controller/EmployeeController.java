package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Employee;
import service.EmpService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private EmpService empbean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("id");
		try {
			Employee employee = empbean.findEmployee(id); 
			request.setAttribute("employee", employee);
			RequestDispatcher req = request.getRequestDispatcher("EmployeeUpdate.jsp"); // change the jsp
			req.forward(request, response);
		} catch (EJBException ex) {

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String extension = request.getParameter("extension");
		String firstname = request.getParameter("firstname");
		String jobtitle = request.getParameter("jobtitle");
		String lastname = request.getParameter("lastname");
		String reportsto = request.getParameter("reportsto");

		String officecode = request.getParameter("officecode");
		PrintWriter out = response.getWriter();
		// this line is to package the whole values into one array string variable -
		// easier just pass one parameter object
		String[] s = { id, email, extension, firstname, jobtitle, lastname,
						reportsto, officecode };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				empbean.updateEmployee(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				empbean.deleteEmployee(id);
			} else {
				empbean.addEmployee(s);
			}
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out, "EmployeeController");

		} catch (EJBException ex) {
		}
	}

}
