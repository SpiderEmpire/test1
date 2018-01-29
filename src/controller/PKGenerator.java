package controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import algorithm.SetupAlgorithm;

@WebServlet("/PKGenerator")
public class PKGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	private int master_key;
	String email;
	int q=5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		master_key=SetupAlgorithm.masterkey();		
	//	Setup();		
		
		
		
		try {
			ServletContext context = getServletContext();
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");
			String subject = "Security key for Samrt Grid system ";
			String content = "Use " + master_key + " as your private key";
			HttpSession session = request.getSession(false);
			if (session != null) {
				email=(String) session.getAttribute("email");			
			}
			int a = EmailUtility.sendEmail(host, port, user, pass, email,
					subject, content);
			if(a==1)
			{
				System.out.println("PK send to  mail "+email);
			}
			else
			{
				System.out.println("error in key sending");
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	

}
