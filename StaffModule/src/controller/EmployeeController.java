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
import service.EmployeeService;
import utilities.ValidateManageLogic;
/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmployeeService empser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			Employee emp = empser.findEmployee(id);
			request.setAttribute("EMP", emp);
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
		// TODO Auto-generated method stub
		// doGet(request, response);
	
		String eid = request.getParameter("id");
		String lname = request.getParameter("lname");
		String fname = request.getParameter("fname");
		String ext = request.getParameter("ext");
		String email = request.getParameter("email");
		String ocode = request.getParameter("ocode");
		String report = request.getParameter("repto");
		String jobt = request.getParameter("jobt");	
		String uname = request.getParameter("uname");

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
			ValidateManageLogic.navigateJSemp(out);
		} catch (EJBException ex) {
		}
	}
}

