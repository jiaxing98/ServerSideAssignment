package utilities;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

public class ValidateManageLogic {
	public static String validateManager(HttpServletRequest request) {
		if (request.getParameter("UPDATE") != null && request.getParameter("UPDATE").equals("UPDATE")) {
			return "UPDATE";
		} else if (request.getParameter("DELETE") != null && request.getParameter("DELETE").equals("DELETE")) {
			return "DELETE";
		}
		return "ADD";
	}

	// this method is used to notify a user that a record has been updated and to
	// redirect to another page
	public static void navigateJSemp(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"EmpPaginationServlet?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}
	
	public static void navigateJSoff(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"OffPaginationServlet?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}
	
	public static void registerAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have successfully registered! Proceed to homepage to login.\")");
		out.println("window.location.assign(\"index.html\")");
		out.println("</SCRIPT>");
	}
	
	public static void signupAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have signed up successfully! Fill up the Register form.\")");
		out.println("</SCRIPT>");
	}

}
