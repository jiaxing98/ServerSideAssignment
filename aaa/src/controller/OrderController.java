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

import domain.Order;
import services.CustomerServiceInterface;
import services.OrderServicesInterface;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderServicesInterface ordersrv;
	
	@Inject
	private CustomerServiceInterface cSrv;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String oNo = request.getParameter("id");
		// EmployeeDAO empdao = new PostgreSQLDbDAOFactory().getEmployeeDAO();
		try {
			Order o = ordersrv.findOrder(oNo);
			request.setAttribute("order", o);
			RequestDispatcher req = request.getRequestDispatcher("OrderUpdate.jsp");
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
		
		/*
		 * String cusName = request.getParameter("q2_fullName2[first]") + " " +
		 * request.getParameter("q2_fullName2[last]"); String conLName =
		 * request.getParameter("c2_fullName2[last]"); String conFName =
		 * request.getParameter("c2_fullName2[first]"); String phone =
		 * request.getParameter("q5_contactNumber[full]"); String address1 =
		 * request.getParameter("q4_billingAddress[addr_line1]"); String address2 =
		 * request.getParameter("q4_billingAddress[addr_line2]"); String city =
		 * request.getParameter("q4_billingAddress[city]"); String state =
		 * request.getParameter("q4_billingAddress[state]"); String postalCode =
		 * request.getParameter("q4_billingAddress[postal]"); String country =
		 * request.getParameter("q4_billingAddress[country]");
		 * 
		 * String[] cusInfo = {cusName, conLName, conFName, phone, address1, address2,
		 * city, state, postalCode, country}; cSrv.addCustomer(cusInfo);
		 */
		
		/*
		 * try { if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
		 * ordersrv.updateOrder(cusInfo); } else if
		 * (ValidateManageLogic.validateManager(request).equals("DELETE")) {
		 * ordersrv.deleteOrder(cusInfo[0]); } else { cSrv.addCustomer(cusInfo); }
		 * 
		 * // this line is to redirect to notify record has been updated and redirect to
		 * // another page
		 * 
		 * } catch (EJBException ex) { }
		 */

		String oNo = request.getParameter("orderNumber");
		String oDate = request.getParameter("oDate");
		String rDate = request.getParameter("rDate");
		String sDate = request.getParameter("sDate");
		String status = request.getParameter("status");
		String comments = request.getParameter("comments");
		String cusNo = request.getParameter("cusNo");
		PrintWriter out = response.getWriter();

		String[] oDetails = { oNo, oDate, rDate, sDate, status, comments, cusNo };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				ordersrv.updateOrder(oDetails);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				ordersrv.deleteOrder(oDetails[0]);
			}
				// add order
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out);
		} catch (EJBException ex) {
		}
	}
}
