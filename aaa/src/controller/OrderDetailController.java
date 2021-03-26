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

import domain.Order;
import domain.Orderdetail;
import services.OrderDetailServiceInterface;
import services.OrderServicesInterface;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OrderDetailController
 */
@WebServlet("/OrderDetailController")
public class OrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Inject
	private OrderDetailServiceInterface odsrv;
	
    public OrderDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String oNo = request.getParameter("id");
		String pCode = request.getParameter("pCode");
		// EmployeeDAO empdao = new PostgreSQLDbDAOFactory().getEmployeeDAO();
		try {
			Orderdetail od = odsrv.findOrderDetail(oNo, pCode);
			request.setAttribute("orderDetail", od);
			RequestDispatcher req = request.getRequestDispatcher("OrderDetailUpdate.jsp");
			req.forward(request, response);
		} catch (EJBException ex) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String oNo = request.getParameter("orderNumber");
		String pdCode = request.getParameter("pdCode");
		String qtyOrdered = request.getParameter("qtyOrdered");
		String prEach = request.getParameter("prEach");
		String oLineNo = request.getParameter("oLineNo");
		
		PrintWriter out = response.getWriter();

		String[] odDetails = { oNo, pdCode, qtyOrdered, prEach, oLineNo };

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				odsrv.updateOrderDetail(odDetails, pdCode);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				odsrv.deleteOrderDetail(odDetails[0], pdCode);
			}
				// add order
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out, "OrderDetail.jsp");
		} catch (EJBException ex) {
		}
	}

}
