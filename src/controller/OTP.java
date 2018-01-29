package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OTP")
public class OTP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int otpold = Integer.parseInt(request.getParameter("otpold"));
		int otpnew = Integer.parseInt(request.getParameter("otpnew"));

		if (otpold == otpnew) {
			response.sendRedirect("Welcome.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please enter correct OTP');");
			out.println("location='Login.jsp';");
			out.println("</script>");

		}
	}

}
