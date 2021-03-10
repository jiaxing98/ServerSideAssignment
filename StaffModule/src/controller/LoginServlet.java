package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserRole;
import service.UserRoleService;
import service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private UserService userbean;
	
	@Inject
	private UserRoleService rolebean;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		PrintWriter out = response.getWriter();
		
		try {
			//if username and password correct -> login session
			//else response username or password is wrong
			if(userbean.loginUser(username, password)) {
				if(validateRole(username)) {
					HttpSession session = request.getSession();
					session.setAttribute("user", username);
					session.setAttribute("role", "user");
					RequestDispatcher req = request.getRequestDispatcher("UserSession.jsp");
					req.forward(request, response);
				}  
				else if(validateRoleAdmin(username)){
					HttpSession session = request.getSession();
					session.setAttribute("admin", username);
					session.setAttribute("role", "admin");
					RequestDispatcher req = request.getRequestDispatcher("adminSession.jsp");
					req.forward(request, response);
				}
				else if(validateRoleStaff(username)){
					HttpSession session = request.getSession();
					session.setAttribute("staff", username);
					session.setAttribute("role", "staff");
					RequestDispatcher req = request.getRequestDispatcher("StaffSession.jsp");
					req.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				out.println("<font color=red>Either user name or password is wrong.</font>");
				dispatcher.include(request, response);
			}
			
		}catch(EJBException ex) {
			
		}
		
	}
	
	private boolean validateRole(String username) {
		List<UserRole> role = rolebean.getAllUserRole(username);
		
		for(int i = 0; i < role.size(); i++) {
			if(role.get(i).getId().getRole().equals("user"))
				return true;
		}
		
		return false;
	}
	
	private boolean validateRoleStaff(String username) {
		List<UserRole> role = rolebean.getAllUserRole(username);
		
		for(int i = 0; i < role.size(); i++) {
			if(role.get(i).getId().getRole().equals("staff"))
				return true;
		}
		
		return false;
	}
	
	private boolean validateRoleAdmin(String username) {
		List<UserRole> role = rolebean.getAllUserRole(username);
		
		for(int i = 0; i < role.size(); i++) {
			if(role.get(i).getId().getRole().equals("admin"))
				return true;
		}
		
		return false;
	}

}