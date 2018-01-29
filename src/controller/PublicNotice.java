package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PublicNotice")
public class PublicNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String date = request.getParameter("date");
		String msg = request.getParameter("msg");
		
		boolean flag = dal.Dbconnect.AddNotice(date, msg);
		if (flag == true) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Notice Send successfully');");
			out.println("location='PublicNotice.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Message Send successfully");
			// response.sendRedirect("Contact.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Some problem to send Notice...Please try again');");
			out.println("location='PublicNotice.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Some problem to send message...");
			// response.sendRedirect("FrmRegistration.jsp");
		}

	}

}
