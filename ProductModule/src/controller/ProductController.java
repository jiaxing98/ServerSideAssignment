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

import domain.Product;
import service.ProductService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Inject
	private ProductService proser;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String productcode = request.getParameter("productcode");
		
		try {
			Product pro = proser.findProduct(productcode);
			request.setAttribute("PRO", pro);
			RequestDispatcher req = request.getRequestDispatcher("ProductUpdate.jsp");
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
		

		String pid = request.getParameter("productcode");
		String pname = request.getParameter("pname");
		String pline = request.getParameter("pline");
		String pscale = request.getParameter("pscale");
		String pvendor = request.getParameter("pvendor");
		String pdescription = request.getParameter("pdescription");
		String quantity = request.getParameter("quantity");
		String buyp = request.getParameter("buyp");
		String rsp = request.getParameter("rsp");
		PrintWriter out = response.getWriter();
		
		String[] p = { pid, pname, pline, pscale, pvendor, pdescription, quantity, buyp, rsp };
		
		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				
				proser.updateProduct(p);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				
				proser.deleteProduct(pid);
				
			} else {
				
				proser.addProduct(p);
			}
		
			ValidateManageLogic.navigateJS(out);
		} catch (EJBException ex) {
		}
	}

}

