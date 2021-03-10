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

import service.UserRoleService;
import service.UserService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/EmpSignUpServlet")
public class EmpSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	private UserService userbean;
	
	@Inject
	private UserRoleService rolebean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpSignUpServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		PrintWriter out = response.getWriter();
		
		try {
			//if username and password correct -> login session
			//else response username or password is wrong
			if(userbean.addUser(username, password)) {
				request.setAttribute("username" , username);

				try {
					rolebean.addUserRole(username, role);
				} catch(EJBException ex) {
					
				}
				
				ValidateManageLogic.signupAlert(out);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/EmpRegister.jsp");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Empsignup.jsp");
				out.println("<font color=red>Username has been used!</font>");
				dispatcher.include(request, response);
			}
			
		}catch(EJBException ex) {
			
		}
		
	}

}