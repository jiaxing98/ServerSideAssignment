package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Orderdetail;
import service.OrderDetailServiceInterface;

/**
 * Servlet implementation class OrderDetailDisplay
 */
@WebServlet("/OrderDetailDisplay")
public class OrderDetailDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Inject
	private OrderDetailServiceInterface odsrv;
	
    public OrderDetailDisplay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int oNo = Integer.valueOf(request.getParameter("id"));
		// EmployeeDAO empdao = new PostgreSQLDbDAOFactory().getEmployeeDAO();
		try {
			List<Orderdetail> odList = odsrv.readOrderDetail(oNo);
			request.setAttribute("orderDetailList", odList);
			RequestDispatcher req = request.getRequestDispatcher("OrderDetail.jsp");
			req.forward(request, response);
		} catch (EJBException ex) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}