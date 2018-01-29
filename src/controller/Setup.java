package controller;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import algorithm.KeyGeneration;

@WebServlet("/Setup")
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String userid, useremail;
	String serverid;
	public static PublicKey spub;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// Information storage(IS)

		HttpSession session = request.getSession(false);
		if (session != null) {
			useremail = (String) session.getAttribute("email");
			userid = (String) session.getAttribute("UId");
		}
		
		Boolean flag1=KeyGeneration.ServerSetup(userid);
		spub=KeyGeneration.getPub();System.out.println("spub"+spub);
		session.setAttribute("ServerPubKey",spub);
		PrivateKey spri=KeyGeneration.getPri();
		
		Boolean flag = KeyGeneration.setup(userid);
		System.out.println(KeyGeneration.getPub());
		if (flag ) {

//			ServletContext context = getServletContext();
//			String host = context.getInitParameter("host");
//			String port = context.getInitParameter("port");
//			String user = context.getInitParameter("user");
//			String pass = context.getInitParameter("pass");
//			String subject = "Samrt Grid Secure Keys";
//			String content = "Use '" + KeyGeneration.getPri()
//					+ "' as your Private Keys ";
//			content += "\n and use '" + KeyGeneration.getPub()
//					+ "' as your public keys.";
//			content+="\n Use '" +spub+ "' as your server public key";
//
//			int a;
//			try {
//				a = EmailUtility.sendEmail(host, port, user, pass, useremail,
//						subject, content);
//				if (a == 1) {
//
					JOptionPane.showMessageDialog(null,"Set up done!!");
//				} else {
//					JOptionPane.showMessageDialog(null, "Sorry,Setup error...");
//
//				}
//			} catch (AddressException e) {
//				
//				e.printStackTrace();
//			} catch (MessagingException e) {
//				
//				e.printStackTrace();
//			}

		} else {
			JOptionPane.showMessageDialog(null, "Key generation error");
		}

		response.sendRedirect("Welcome.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
