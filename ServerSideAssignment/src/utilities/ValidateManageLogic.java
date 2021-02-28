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
	public static void navigateJS(PrintWriter out, String directedURL) {	//subject to change
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"" + directedURL + "?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}
	
	public static void navigateLogin(PrintWriter out, boolean value) {
		if(value) {
			out.println("<SCRIPT type=\"text/javascript\">");
			out.println("alert(\"You have login successfully!\")");
			out.println("window.location.assign(\"index.html\")");
			out.println("</SCRIPT>");
		} else {
			out.println("<SCRIPT type=\"text/javascript\">");
			out.println("alert(\"Wrong username or password!\")");
			out.println("window.location.assign(\"login.jsp\")");
			out.println("</SCRIPT>");
		}
	}
	
	public static void navigateSignUp(PrintWriter out, boolean value) {
		if(value) {
			out.println("<SCRIPT type=\"text/javascript\">");
			out.println("alert(\"You have signed up successfully! Proceed homepage to login.\")");
			out.println("window.location.assign(\"index.html\")");
			out.println("</SCRIPT>");
		} else {
			out.println("<SCRIPT type=\"text/javascript\">");
			out.println("alert(\"The username has been used!\")");
			out.println("window.location.assign(\"signup.jsp\")");
			out.println("</SCRIPT>");
		}
	}

}
