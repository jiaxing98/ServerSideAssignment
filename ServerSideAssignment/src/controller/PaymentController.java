package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PaymentPK;
import service.PaymentService;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
	private PaymentService paymentbean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String customernumber = request.getParameter("customernumber");
		String checknumber = request.getParameter("checknumber");
		String amount = request.getParameter("amount");
		String paymentdate = request.getParameter("paymentdate");
		String paymentmethod = request.getParameter("paymentmethod");
		PrintWriter out = response.getWriter();

		PaymentPK paymentPK = new PaymentPK();
		paymentPK.setCustomernumber(Integer.parseInt(customernumber));
		paymentPK.setChecknumber(checknumber);
		
		String[] s = { amount, paymentdate, paymentmethod };
		
		try {
			if (ValidateManageLogic.validateManager(request).equals("DELETE")) {
				paymentbean.deletePayment(paymentPK);
			} else {
				paymentbean.addPayment(paymentPK, s);
			}
			
			ValidateManageLogic.navigateJS(out, "PaymentPagination");
			
		} catch (EJBException ex) {
		}	
	}

}
