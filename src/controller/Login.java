package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dal.Dbconnect;
import services.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = false;
	String resultMessage;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String uname = request.getParameter("txtuname");
		String pwd = request.getParameter("txtpassword");
		String r1 = request.getParameter("r1");

		User cl = new User();
		cl.setUsername(uname);
		cl.setPwd(pwd);
		if (r1.equals("admin")) {
			flag = Dbconnect.adminlogin(cl);
			if (flag == true) {
				HttpSession session = request.getSession();
				
				ServletContext context = getServletContext();
				String path = context.getInitParameter("path");
				new File(path).mkdir();
				new File(path+"admin").mkdirs();

				session.setAttribute("name", "Admin");
				session.setAttribute("region", "admin");
				response.sendRedirect("admin.jsp");
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please Check username and password..');");
				out.println("location='Login.jsp';");
				out.println("</script>");
			}
		} else {

			if (Dbconnect.login(cl)) {
				ServletContext context = getServletContext();
				String path = context.getInitParameter("path");
				
				new File(path).mkdir();
				HttpSession session = request.getSession();
				String userdata[] = Dbconnect.getUserDetails(cl.getUsername());
				session.setAttribute("uid", userdata[0]);
				session.setAttribute("name", userdata[1]);
				session.setAttribute("region", userdata[2]);
				String email=userdata[5];
				session.setAttribute("email",  userdata[5]);
				session.setAttribute("meterno", userdata[3]);
				session.setAttribute("username", uname);
				try {
					Random r = new Random();
					String otp = new String();
					for (int i = 0; i < 8; i++) {
						otp += r.nextInt(10);
					}
					System.out.println(otp);
					
					String host = context.getInitParameter("host");
					String port = context.getInitParameter("port");
					String user = context.getInitParameter("user");
					String pass = context.getInitParameter("pass");
					String subject = "Smart Grid Email verification";
					String content = "'Secure smart grid' Your OTP for consumer number '"+ userdata[11]+"' is " + otp+" .";

					 int a = EmailUtility.sendEmail(host, port, user, pass,
					 email, subject, content);
					//int a = 1;
					if (a == 1) {

						resultMessage = "The OTP send to your email address";
						request.setAttribute("Message", resultMessage);
						request.setAttribute("otp", otp);
					} else {

						PrintWriter out = response.getWriter();
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Login process interrupted.Please try again..');");
						out.println("location='index.jsp';");
						out.println("</script>");

					}
				} catch (Exception ex) {
					ex.printStackTrace();
					resultMessage = "There were an error: " + ex.getMessage();
				} 

				getServletContext().getRequestDispatcher("/OTP.jsp").forward(
						request, response);

			} else {
				
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please Check username and password..');");
				out.println("location='Login.jsp';");
				out.println("</script>");
				
			}
		}
	}

}
