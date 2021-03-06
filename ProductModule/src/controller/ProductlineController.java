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

import domain.Productline;
import service.ProductlineService;
import utilities.ValidateManageLogic;

@WebServlet("/ProductlineController")
public class ProductlineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private ProductlineService plineser;
	
	public ProductlineController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String productline = request.getParameter("productline");

		try {
			Productline prol = plineser.findProductline(productline);
			request.setAttribute("PROL", prol);
			RequestDispatcher req = request.getRequestDispatcher("ProductlineUpdate.jsp");
			req.forward(request, response);
		} catch (EJBException ex) {
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String productline = request.getParameter("prol");
		String textdescription = request.getParameter("textd");
		String htmldescription = request.getParameter("htmld");
		String image = request.getParameter("img");
		PrintWriter out = response.getWriter();
		
		String[] s = {productline,textdescription,htmldescription,image};
		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				
				plineser.updateProductline(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				
				plineser.deleteProductline(productline);
				
			} else {
				
				plineser.addProductline(s);
			}
		
			ValidateManageLogic.navigateJSpl(out);
		} catch (EJBException ex) {
		}
	}

	
	

}
