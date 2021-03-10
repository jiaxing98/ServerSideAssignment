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

	public static void navigateJS(PrintWriter out, String directedURL) {	//subject to change
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"" + directedURL + "?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}
	
	public static void registerAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have successfully registered! Proceed to homepage to login.\")");
		out.println("window.location.assign(\"index.html\")");
		out.println("</SCRIPT>");
	}
	
	public static void EmpregisterAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have successfully registered a new employee! You may try to log in the new employee account\")");
		out.println("window.location.assign(\"index.html\")");
		out.println("</SCRIPT>");
	}
	
	public static void signupAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have signed up successfully! Fill up the Register form.\")");
		out.println("</SCRIPT>");
	}
	
	public static void logoutAlert(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"You have logged out successfully!\")");
		out.println("window.location.assign(\"index.html\")");
		out.println("</SCRIPT>");
	}

}
