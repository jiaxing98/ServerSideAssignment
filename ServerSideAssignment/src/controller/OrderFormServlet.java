package controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Customer;
import service.CustomerServiceInterface;

/**
 * Servlet implementation class OrderFormServlet
 */
@WebServlet("/OrderFormServlet")
public class OrderFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Inject
    private CustomerServiceInterface cusSrv;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int totalItem = Integer.valueOf(request.getParameter("totalItem"));
		for (int i = 1; i <= totalItem; i++) {
			String pdLine = request.getParameter("pdName" + i);
			String pdName = request.getParameter("pdLine" + i);
			int buyQty = Integer.valueOf(request.getParameter("buyQty" + i));
			float totalPrice = Float.valueOf(request.getParameter("totalPrice" + i));
			
			request.setAttribute("pdLine"+i, pdLine);
			request.setAttribute("pdName"+i, pdName);
			request.setAttribute("buyQty"+i, buyQty);
			request.setAttribute("totalPrice"+i, totalPrice);
		}
		request.setAttribute("totalItem", totalItem);	
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		Customer cus = cusSrv.findCustomerbyUsername(username);
		request.setAttribute("customer", cus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("OrderForm.jsp");
		dispatcher.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}