package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmpService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/EmpRegisterServlet")
public class EmpRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private EmpService empbean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpRegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		 
		ValidateManageLogic.EmpregisterAlert(out);
	}
	
}