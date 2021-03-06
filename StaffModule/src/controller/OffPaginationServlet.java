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

import domain.Office;
import service.OfficeService;

/**
 * Servlet implementation class OffPaginationServlet
 */
@WebServlet("/OffPaginationServlet")
public class OffPaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OfficeService offser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OffPaginationServlet() {
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

		response.setContentType("text/html;charset=UTF-8");
		// Write some codes here…
		int nOfPages = 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		String keyword = request.getParameter("keyword");
		try {
			int rows = offser.getNumberOfRows(keyword);
			nOfPages = rows / recordsPerPage;
			System.out.println("At servlet" + nOfPages);
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}
			if (currentPage > nOfPages && nOfPages != 0) {
				currentPage = nOfPages;
			}
			List<Office> lists = offser.readOffice(currentPage, recordsPerPage, keyword);
			request.setAttribute("offices", lists);
		} catch (EJBException ex) {
		}
		request.setAttribute("nOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("keyword", keyword);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Offpagination.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
