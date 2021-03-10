package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.ejb.EJB;
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
	private EmpService empser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String role = request.getParameter("role");

		try {
			Employee emp = empser.findEmployee(id);
			request.setAttribute("EMP", emp);
			request.setAttribute("username", username);
			request.setAttribute("role", role);
			RequestDispatcher req = request.getRequestDispatcher("EmployeeUpdate.jsp");
			req.forward(request, response);
		} catch (EJBException ex) {
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String eid = request.getParameter("id");
		String lname = request.getParameter("lname");
		String fname = request.getParameter("fname");
		String ext = request.getParameter("ext");
		String email = request.getParameter("email");
		String ocode = request.getParameter("ocode");
		String report = request.getParameter("repto");
		String jobt = request.getParameter("jobt");	
		String uname = request.getParameter("uname");
		String username = request.getParameter("username");
		String role = request.getParameter("role");

		PrintWriter out = response.getWriter();
		
		String[] s = { eid, fname, lname, ext, email, ocode, report, jobt, uname };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				// call session bean updateEmployee method
				empser.updateEmployee(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				// call session bean deleteEmployee method
				empser.deleteEmployee(eid);
				// if ADD button is clicked
			} else {
				// call session bean addEmployee method
				empser.addEmployee(s);
			}
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			request.setAttribute("username", username);
			request.setAttribute("role", role);
			ValidateManageLogic.navigateJS(out, "EmpPaginationServlet");
		} catch (EJBException ex) {
		}
	}
}