package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boolean flag = false;
	int uid=0;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String consumer = request.getParameter("consumer");
		String nm = request.getParameter("name");
		String email = request.getParameter("email");
		String msg = request.getParameter("msg");
		String phone = request.getParameter("phone");
		String region = request.getParameter("region");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		HttpSession session = request.getSession();
		if (session != null) {
			uid = Integer.parseInt((String) session.getAttribute("uid"));
		}

		flag = dal.Dbconnect.AddComplaint(consumer, nm, email, msg, phone,
				region, address, type,uid);
		if (flag == true) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Message Send successfully');");
			out.println("location='Complaint.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Message Send successfully");
			// response.sendRedirect("Contact.jsp");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Some problem to send message...');");
			out.println("location='Complaint.jsp';");
			out.println("</script>");
			// JOptionPane.showMessageDialog(null,"Some problem to send message...");
			// response.sendRedirect("FrmRegistration.jsp");
		}
	}
}
