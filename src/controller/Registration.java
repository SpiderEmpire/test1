package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.Dbconnect;
//import net.codejava.email.EmailUtility;
import services.User;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = false;
	String resultMessage = "";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// response.setHeader("");
		try {
			String fnm = request.getParameter("txtfnm");
			String lnm = request.getParameter("txtlnm");
			String email = request.getParameter("txtemail");
			String phone = request.getParameter("txtphone");
			String pwd = request.getParameter("txtpassword");
			String meterNo = request.getParameter("txtmeterno");
			String region = request.getParameter("region");
			String username = request.getParameter("txtuserid");
			String address = request.getParameter("address");
			String capchacode = request.getParameter("txtcode");
			String consumer=request.getParameter("txtconsumer");

			String captcha = (String) request.getSession().getAttribute(
					"captcha");

			/*
			 * Random r = new Random(); String otp = new String(); for(int i=0 ;
			 * i < 8 ; i++) { otp += r.nextInt(10); } System.out.println(otp);
			 * 
			 * ServletContext context = getServletContext(); String host =
			 * context.getInitParameter("host"); String port =
			 * context.getInitParameter("port"); String user =
			 * context.getInitParameter("user"); String pass =
			 * context.getInitParameter("pass"); String
			 * subject="Samrt Grid Email verification"; String content="Use "+
			 * otp +" as your otp";
			 * 
			 * try { int a=EmailUtility.sendEmail(host, port, user, pass, email,
			 * subject,content); if(a==1) { System.out.println(a); resultMessage
			 * = "The e-mail was sent successfully"; } else {
			 * System.out.println(a+"error"); } } catch (Exception ex) {
			 * ex.printStackTrace(); resultMessage = "There were an error: " +
			 * ex.getMessage(); } finally { //request.setAttribute("Message",
			 * resultMessage);
			 * //getServletContext().getRequestDispatcher("/OTP.jsp"
			 * ).forward(request, response); }
			 */

			User cl = new User();

			cl.setFnm(fnm);
			cl.setLnm(lnm);
			cl.setConsumer(consumer);
			cl.setEmail(email);
			cl.setPhone(phone);
			cl.setPwd(pwd);
			cl.setUsername(username);
			cl.setMeterNo(meterNo);
			cl.setRegion(region);
			cl.setAddress(address);
			// cl.setPin(pin);

			if (capchacode.equals(captcha)) {

				int uid = Dbconnect.registration(cl);

				if (uid != 0) {

					// int id=Dbconnect.getLastUId();
					ServletContext context = getServletContext();
					String path = context.getInitParameter("path");

					new File(path).mkdir();
					// new File(path+region).mkdirs();
					String str = path + "\\" + uid + ".txt"; // System.out.println(str);
					File f = new File(str);
					f.createNewFile();
					String filecontent = "SavingDate FromDate ToDate MeterReading";
					FileWriter fw = new FileWriter(f);
					fw.write(filecontent);
					fw.flush();
					fw.close();

					AmazonStorage.uploadUsage(uid, uid + ".txt", f, region);

					// JOptionPane.showMessageDialog(null,"Registered Successfully...Login to continue");
					// request.setAttribute("Message", resultMessage);
					// request.setAttribute("otp", otp);
					// getServletContext().getRequestDispatcher("/OTP.jsp").forward(request,
					// response);
					// PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Registered Successfully.Login to Continue');");
					out.println("location='Login.jsp';");
					out.println("</script>");
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert(' please enter all correct details or user already exist');");
					out.println("location='Registration.jsp';");
					out.println("</script>");
					// JOptionPane.showMessageDialog(null,"This user already Exist please enter all correct details");
					// response.sendRedirect("Registration.jsp");
				}
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert(' please enter correct captcha code');");
				out.println("location='Registration.jsp';");
				out.println("</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
