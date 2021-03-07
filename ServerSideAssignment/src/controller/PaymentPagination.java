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
import javax.servlet.http.HttpSession;

import domain.Payment;
import service.PaymentService;

/**
 * Servlet implementation class PaymentPagination
 */
@WebServlet("/PaymentPagination")
public class PaymentPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private PaymentService paymentbean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentPagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int nOfPages = 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		String keyword = request.getParameter("keyword");
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		try {
			int rows;
			
			if(role.equals("user")) {
				rows = paymentbean.userGetNumberOfRows(keyword, username);
			} else if (role.equals("staff")) {
				rows = paymentbean.staffGetNumberOfRows(keyword, username);
			}
			else {
				rows = paymentbean.adminGetNumberOfRows(keyword);
			}
			
			nOfPages = rows / recordsPerPage;
			System.out.println("At servlet" + nOfPages);
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}

			if (currentPage > nOfPages && nOfPages != 0) {
				currentPage = nOfPages;
			}

			if(role.equals("user")) {
				List<Payment> lists = paymentbean.userReadRecords(currentPage, recordsPerPage, keyword, username);
				request.setAttribute("payment", lists);
			} else if (role.equals("staff")) {
				List<Payment> lists = paymentbean.staffReadRecords(currentPage, recordsPerPage, keyword, username);
				request.setAttribute("payment", lists);
			}
			else {
				List<Payment> lists = paymentbean.adminReadRecords(currentPage, recordsPerPage, keyword);
				request.setAttribute("payment", lists);
			}
			
		} catch (EJBException ex) {

		}
		
		request.setAttribute("nOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("keyword", keyword);
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
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
