package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CustomerService;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private CustomerService customerbean;
	
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
		
		String cno = request.getParameter("id");
		String address1 = request.getParameter("addressline1");
		String address2 = request.getParameter("addressline2");
		String city = request.getParameter("city");
		String contactfname = request.getParameter("contactfname");
		String contactlname = request.getParameter("contactlname");
		String country = request.getParameter("country");
		String creditlimit = request.getParameter("creditlimit");

		String customername = request.getParameter("customername");
		String phone = request.getParameter("phone");
		String postalcode = request.getParameter("postalcode");
		String state = request.getParameter("state");
		String empno = request.getParameter("empno");
		String username = request.getParameter("username");
		PrintWriter out = response.getWriter();
		
		String[] s = { cno, address1, address2, city, contactfname, contactlname,
				country, creditlimit, customername, phone, postalcode, state, empno, username };
	
		customerbean.addCustomer(s);
		
		response.sendRedirect("index.html");
	}

}
