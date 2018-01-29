package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dal.Dbconnect;

@WebServlet("/UserStatus")
public class UserStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// String email = request.getParameter("txtuname");
		// String pwd = request.getParameter("txtpassword");
		int uid = Integer.parseInt(request.getParameter("uuid"));
		int status = Integer.parseInt(request.getParameter("chk"));
		if (status == 1) {
			status = 0;
		} else {
			status=1;
		}
		//System.out.println("hi..." + uid);
		//System.out.println("chk" + status);

		Boolean flag = Dbconnect.changeUserStatus(uid, status);
		PrintWriter out = response.getWriter();
		if (flag == true) {

			out.println("<script type=\"text/javascript\">");
			out.println("alert('User Status updated sucessfully ...');");
			out.println("location='ManageUsers.jsp?filter=All';");
			out.println("</script>");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('failed to change user status..please try again..');");
			out.println("location='ManageUsers.jsp?filter=All';");
			out.println("</script>");
		}

	}
}
