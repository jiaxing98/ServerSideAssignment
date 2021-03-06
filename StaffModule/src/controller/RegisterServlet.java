package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmployeeService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private EmployeeService empbean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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

		PrintWriter out = response.getWriter();
		
		String[] s = { eid, fname, lname, ext, email, ocode, report, jobt, uname };
		  
		empbean.addEmployee(s);
		 
		ValidateManageLogic.registerAlert(out);
	}
	
}