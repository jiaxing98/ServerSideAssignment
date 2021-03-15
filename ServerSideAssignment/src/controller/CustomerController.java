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
import javax.servlet.http.HttpSession;

import domain.Customer;
import service.CustomerService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private CustomerService customerbean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerController() {
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

		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		try {
			if (id != null) {
				Customer customer = customerbean.findCustomer(id);
				request.setAttribute("customer", customer);
				request.setAttribute("role", role);
				RequestDispatcher req = request.getRequestDispatcher("CustomerUpdate.jsp");
				req.forward(request, response);
			} else {
				Customer customer = customerbean.findCustomerbyUsername(username);
				request.setAttribute("customer", customer);
				request.setAttribute("role", role);
				RequestDispatcher req = request.getRequestDispatcher("CustomerAccount.jsp");
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

		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		PrintWriter out = response.getWriter();
		// this line is to package the whole values into one array string variable -
		// easier just pass one parameter object
		String[] s = { cno, address1, address2, city, contactfname, contactlname, country, creditlimit, customername,
				phone, postalcode, state, empno, username };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				customerbean.updateCustomer(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				customerbean.deleteCustomer(cno);
			} else if (ValidateManageLogic.validateManager(request).equals("REMOVE")) {
				customerbean.removeCustomer(cno, empno);
			} else {
				customerbean.addCustomer(s);
			}

			if (role.equals("admin")) {
				ValidateManageLogic.navigateJS(out, "CustomerPagination");
			} else if (role.equals("staff")) {
				ValidateManageLogic.navigateJS(out, "CustomerPagination");

			} else if (role.equals("user")) {
				Customer customer = customerbean.findCustomer(cno);
				request.setAttribute("customer", customer);
				RequestDispatcher req = request.getRequestDispatcher("CustomerAccount.jsp");
				req.forward(request, response);
			}

		} catch (EJBException ex) {
		}

	}

}
