package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ContactUs")
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		
   		
   		int uid;
		boolean flag;
		String nm = request.getParameter("name");
		String email = request.getParameter("email");
		String msg = request.getParameter("msg");
		String phone = request.getParameter("phone");
		
		

		flag = dal.Dbconnect.AddContactUS(nm, email, msg, phone);
		if (flag == true) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Message Send successfully');");
			out.println("location='ContactUs.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Message Send successfully");
			// response.sendRedirect("Contact.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Some problem to send message...');");
			out.println("location='ContactUs.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Some problem to send message...");
			// response.sendRedirect("FrmRegistration.jsp");
		}
	}

}
