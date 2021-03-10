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

import domain.Employee;
import service.EmpService;

/**
 * Servlet implementation class EmpPaginationServlet
 */
@WebServlet("/EmpPaginationServlet")
public class EmpPaginationServlet extends HttpServlet {

	@Inject
	private EmpService empser;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpPaginationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		int nOfPages = 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		String keyword = request.getParameter("keyword");
		
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");
		
		try {

			if (role.equals("admin")) {
				int rows = empser.adminGetNumberOfRows(keyword);
				nOfPages = rows / recordsPerPage;
				System.out.println("At servlet" + nOfPages);
				if (rows % recordsPerPage != 0) {
					nOfPages++;
				}
				if (currentPage > nOfPages && nOfPages != 0) {
					currentPage = nOfPages;
				}
				List<Employee> lists = empser.adminReadRecords(currentPage, recordsPerPage, keyword);
				request.setAttribute("staffs", lists);
			} else if (role.equals("staff")) {
				int rows = empser.staffGetNumberOfRows(keyword, username);
				nOfPages = rows / recordsPerPage;
				System.out.println("At servlet" + nOfPages);
				if (rows % recordsPerPage != 0) {
					nOfPages++;
				}
				if (currentPage > nOfPages && nOfPages != 0) {
					currentPage = nOfPages;
				}
				List<Employee> lists = empser.staffReadRecords(currentPage, recordsPerPage, keyword, username);
				request.setAttribute("staffs", lists);
			}
		} catch (EJBException ex) {
		}
		request.setAttribute("nOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("keyword", keyword);
		request.setAttribute("username", username);
		request.setAttribute("role", role);
		RequestDispatcher dispatcher;
		if (role.equals("admin")) {
			dispatcher = request.getRequestDispatcher("Emppagination.jsp");
			dispatcher.forward(request, response);
		}
		else if (role.equals("staff")) {
			dispatcher = request.getRequestDispatcher("StaffDetail.jsp");
			dispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * String username = request.getParameter("username"); String role =
		 * request.getParameter("role"); try {
		 * 
		 * }
		 * 
		 * 
		 * 
		 * request.setAttribute("username", username); request.setAttribute("role",
		 * role);
		 */

	}

}