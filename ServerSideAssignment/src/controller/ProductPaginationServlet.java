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

import domain.Product;
import service.ProductService;

/**
 * Servlet implementation class ProductPagninationServlet
 */
@WebServlet("/ProductPaginationServlet")
public class ProductPaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProductService proser;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPaginationServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("type/html;charset=UTF-8");
		int nOfPages = 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		String keyword = request.getParameter("keyword");

		try {
			int rows = proser.getNumberOfRows(keyword);
			nOfPages = rows / recordsPerPage;
			System.out.println("At servlet" + nOfPages);
			if (rows % recordsPerPage != 0) {
				nOfPages++;
			}
			if (currentPage > nOfPages && nOfPages != 0) {
				currentPage = nOfPages;
			}
			List<Product> lists = proser.readProduct(currentPage, recordsPerPage, keyword);
			request.setAttribute("products", lists);
		} catch (EJBException ex) {
		}
		request.setAttribute("nOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("keyword", keyword);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ProductPagination.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}