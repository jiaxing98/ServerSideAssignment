package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
			Customer customer = customerbean.findCustomer(id); 
			request.setAttribute("Customer", customer);
			RequestDispatcher req = request.getRequestDispatcher("EmployeeUpdate.jsp"); // change the jsp
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

		String cno = request.getParameter("cno");
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
		PrintWriter out = response.getWriter();
		// this line is to package the whole values into one array string variable -
		// easier just pass one parameter object
		String[] s = { cno, address1, address2, city, contactfname, contactlname,
						country, creditlimit, customername, phone, postalcode, state };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				customerbean.updateCustomer(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				customerbean.deleteCustomer(cno);
			} else {
				customerbean.addCustomer(s);
			}
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out);

		} catch (EJBException ex) {
		}

	}

}
