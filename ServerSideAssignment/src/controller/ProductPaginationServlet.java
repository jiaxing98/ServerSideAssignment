package controller;

import java.io.IOException;
import java.util.ArrayList;
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

import domain.Product;
import domain.Productline;
import service.ProductServiceInterface;
import service.ProductlineServiceInterface;

/**
 * Servlet implementation class ProductPagination
 */
@WebServlet("/ProductPaginationServlet")
public class ProductPaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ProductlineServiceInterface pdlbean;

	@Inject
	private ProductServiceInterface pbean;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductPaginationServlet() {
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

		int nOfPages = 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		String keyword = request.getParameter("keyword");
		int nOfPdlFilter = Integer.valueOf(request.getParameter("nOfPdlFilter"));
		int totalItem = Integer.valueOf(request.getParameter("totalItem"));
		String pdlSelected;

		List<Productline> pdlist = pdlbean.getAllProductline();
		request.setAttribute("productline", pdlist);

		try {
			int rows = pbean.getNumberOfRows(keyword);
			nOfPages = rows / recordsPerPage;
			System.out.println("At servlet" + nOfPages);
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}
			if (currentPage > nOfPages && nOfPages != 0) {
				currentPage = nOfPages;
			}

			List<Product> lists = new ArrayList<>();

			if (nOfPdlFilter == 0) {
				pdlSelected = "";
				lists.addAll(pbean.readProduct(currentPage, recordsPerPage, keyword, pdlSelected));
			} else {
				for (int i = 1; i < nOfPdlFilter; i++) {
					pdlSelected = request.getParameter("pdlFilter" + i);
					lists.addAll(pbean.readProduct(currentPage, recordsPerPage, keyword, pdlSelected));
				}
			}

			request.setAttribute("products", lists);
		} catch (EJBException ex) {
		}

		if (totalItem > 0) {
			for (int i = 1; i <= totalItem; i++) {
				String pdLine = request.getParameter("pdLine" + i);
				String pdName = request.getParameter("pdName" + i);
				int buyQty = Integer.valueOf(request.getParameter("buyQty" + i));
				float totalPrice = Float.valueOf(request.getParameter("totalPrice" + i));

				request.setAttribute("pdLine" + i, pdLine);
				request.setAttribute("pdName" + i, pdName);
				request.setAttribute("buyQty" + i, buyQty);
				request.setAttribute("totalPrice" + i, totalPrice);
			}
		}

		request.setAttribute("nOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("keyword", keyword);
		request.setAttribute("nOfPdlFilter", nOfPdlFilter);
		request.setAttribute("totalItem", totalItem);
		HttpSession session = request.getSession();
		
		if (session.getAttribute("username") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerProductView.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("Order.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}