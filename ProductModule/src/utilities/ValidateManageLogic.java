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

	
	public static void navigateJS(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"ProductPaginationServlet?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}
	
	public static void navigateJSpl(PrintWriter out) {
		out.println("<SCRIPT type=\"text/javascript\">");
		out.println("alert(\"Record has been updated and url will be redirected\")");
		out.println("window.location.assign(\"ProductlinePaginationServlet?currentPage=1&recordsPerPage=70&keyword=\")");
		out.println("</SCRIPT>");
	}


}
