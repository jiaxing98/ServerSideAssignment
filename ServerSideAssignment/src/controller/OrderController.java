package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

import domain.Customer;
import domain.Order;
import domain.Orderdetail;
import domain.Product;
import service.CustomerServiceInterface;
import service.OrderDetailServiceInterface;
import service.OrderServiceInterface;
import service.ProductServiceInterface;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private OrderServiceInterface ordersrv;
	
	@Inject
	private OrderDetailServiceInterface odsrv;
	
	@Inject
	private CustomerServiceInterface cSrv;
	
	@Inject
	private ProductServiceInterface pSrv;

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
		
		String oNo = request.getParameter("orderNumber");
		String oDate = request.getParameter("oDate");
		String rDate = request.getParameter("rDate");
		String sDate = request.getParameter("sDate");
		String status = request.getParameter("status");
		String comments = request.getParameter("comments");
		String cusNo = request.getParameter("cusNo");
		PrintWriter out = response.getWriter();
		String path ="OrderPagination";
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Customer cus = cSrv.findCustomerbyUsername(username);
		/*
		 * List<Orderdetail>odList = odsrv.getAllOrderNo(); for(Orderdetail od : odList)
		 * { odNo.add(String.valueOf(od.getOrder().getOrdernumber())); }
		 */

		String[] oInfo = { oNo, oDate, rDate, sDate, status, comments, cusNo };
		
		List<String[]> oDetails = new ArrayList<String[]>();
		List<Product> product = new ArrayList<Product>();
		
		int totalItem = Integer.valueOf(request.getParameter("totalItem"));
		if(totalItem > 0) {
			for(int i=1; i<=totalItem; i++) {
				String productName = request.getParameter("pdName" + i);
				String pCode = pSrv.findProductCode(productName);
				product.add(pSrv.findProduct(pCode));
				int buyQty = Integer.valueOf(request.getParameter("buyQty" + i));
				float totalPrice = Float.valueOf(request.getParameter("totalPrice" + i));
				float prEach = totalPrice / buyQty;
				int oLineNo = 1;
				
				String[] details = {Integer.toString(buyQty), Float.toString(prEach), Integer.toString(oLineNo)};
				oDetails.add(details);
			}
		}
		
		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				ordersrv.updateOrder(oInfo);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				ordersrv.deleteOrder(oInfo[0]);
			} else	{
				ordersrv.addOrder(oInfo, oDetails, product, cus);
				path = "Order.jsp";
			}
				// add order
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out, path);
		} catch (EJBException ex) {
		}
	}
}