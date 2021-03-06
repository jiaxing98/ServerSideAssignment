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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String id = request.getParameter("id");
		String pname = request.getParameter("pname");
		String pline = request.getParameter("pline");
		String pscale = request.getParameter("pscale");
		String pvendor = request.getParameter("pvendor");
		String pdes = request.getParameter("pdes");
		String qtyinstock = request.getParameter("qtyinstock");
		String buyprice = request.getParameter("buyprice");
		String msrp = request.getParameter("msrp");
		PrintWriter out = response.getWriter();
		
		String[] s = { id,pname, pline, pscale,pvendor,pdes,qtyinstock,buyprice,msrp};

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				// call session bean updateEmployee method
				proser.updateProduct(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				// call session bean deleteEmployee method
				proser.deleteProduct(id);
				// if ADD button is clicked
			} else {
				// call session bean addEmployee method
				proser.addProduct(s);
			}
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJS(out);
		} catch (EJBException ex) {
		}
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("pcode");
		
		try {
			Product pro = proser.findProduct(id);
			request.setAttribute("PRO", pro);
			RequestDispatcher req = request.getRequestDispatcher("ProductUpdate.jsp");
			req.forward(request, response);
		} catch (EJBException ex) {
		}
	}

}
