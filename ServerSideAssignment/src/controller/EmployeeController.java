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
import javax.servlet.http.HttpSession;

import domain.Customer;
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
		HttpSession session = request.getSession(false);

		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		try {
			if (id != null) {
				Employee EMP = empser.findEmployee(id);
				request.setAttribute("EMP", EMP);
				//request.setAttribute("username", username);
				request.setAttribute("role", role);
				RequestDispatcher req = request.getRequestDispatcher("EmployeeUpdate.jsp");
				req.forward(request, response);
			} else {
				Employee EMP = empser.findEmployeebyUsername(username);
				request.setAttribute("EMP", EMP);
				request.setAttribute("role", role);
				RequestDispatcher req = request.getRequestDispatcher("StaffDetail.jsp");
				req.forward(request, response);
			}
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
		//String username = request.getParameter("username");
		//String role = request.getParameter("role");
		HttpSession session = request.getSession(false);
		//String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		PrintWriter out = response.getWriter();

		String[] s = { eid, lname, fname, ext, email, ocode, report, jobt, uname };

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
			if(role.equals("admin")) {
				ValidateManageLogic.navigateJS(out, "EmpPaginationServlet");
			} else {
				Employee EMP = empser.findEmployee(eid);
				request.setAttribute("EMP", EMP);
				RequestDispatcher req = request.getRequestDispatcher("StaffDetail.jsp");
				req.forward(request, response);
			}
		} catch (EJBException ex) {
		}
	}
}