package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.UserRole;
import service.UserRoleService;
import service.UserService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private UserService userbean;
	private EntityManager em;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserRoleService service = new UserRoleService(em);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		
		try {
			//if username and password correct -> login session
			//else response username or password is wrong
			if(userbean.loginUser(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setMaxInactiveInterval(60*60);
				Cookie userName = new Cookie("user", username);
				userName.setMaxAge(-1);
				response.addCookie(userName);
				
				List<UserRole> role = service.getAllUserRole(username);
				for(int i = 0; i < role.size(); i++) {
					if(role.get(i).getId().getRole() == "user") {
						response.sendRedirect("LoginSuccess.jsp");
					}
					else {
						response.sendRedirect("index.html");
					}
				}

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
				out.println("<font color=red>Either user name or password is wrong.</font>");
				dispatcher.include(request, response);
			}
			
		}catch(EJBException ex) {
			
		}
		
	}

}
