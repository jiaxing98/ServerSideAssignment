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

import domain.Office;
import service.OfficeService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OfficeController
 */
@WebServlet("/OfficeController")
public class OfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private OfficeService offser;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OfficeController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		try {
			Office off = offser.findOffice(id);
			request.setAttribute("OFF", off);
			RequestDispatcher req = request.getRequestDispatcher("OfficeUpdate.jsp");
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
		
		String ocode = request.getParameter("id");
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String adr1 = request.getParameter("adr1");
		String adr2 = request.getParameter("adr2");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String pcode = request.getParameter("pcode");
		String ter = request.getParameter("terr");
		PrintWriter out = response.getWriter();
		
		String[] s = { ocode, city, phone, adr1, adr2, state, country, pcode, ter};

		try {
			if (ValidateManageLogic.validateManager(request).equals("UPDATE")) {
				// call session bean updateEmployee method
				offser.updateOffice(s);
			} else if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				// call session bean deleteEmployee method
				offser.deleteOffice(ocode);
				// if ADD button is clicked
			} else {
				// call session bean addEmployee method
				offser.addOffice(s);
			}
			// this line is to redirect to notify record has been updated and redirect to
			// another page
			ValidateManageLogic.navigateJSoff(out);
		} catch (EJBException ex) {
		}
	}

}
