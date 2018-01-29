package controller;

import java.math.BigInteger;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import CentralAuthority.PKG;
import algorithm.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;

import dal.Dbconnect;
import services.User;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/SaveData")
public class SaveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message = "";
	static String uid;
	static String email;
	String filenm;
	static PublicKey spub;
	int userid;
	String region;
	String meterno;
	static PKG pkg = new PKG();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		String path = context.getInitParameter("path");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		if (session != null) {
			userid =  Integer.parseInt((String) session.getAttribute("uid"));
			region = (String) session.getAttribute("region");
		}
		//System.out.println("userid"+userid+"region"+region);
		try {
			//call setup			
			
			String serverids[]=Dbconnect.getserverid(Integer.toString(userid));
			if(algorithm.Setup.setup(Integer.toString(userid),serverids[1]))
			{
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('setup done');");
				
				out.println("</script>");	
			}
			String fromdate=request.getParameter("txtfromdate");
			String todate=request.getParameter("txttodate");
			meterno=request.getParameter("txtmeterno");
			int usage = Integer.parseInt(request.getParameter("txtUsage"));
			String Algo = request.getParameter("IBC");

			message = "\n"+ dateFormat.format(date)+" "+usage;

			if (Algo.equals("IBE")) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Your');");
				//out.println("location='Welcome.jsp';");
				out.println("</script>");	

				System.out.println("original msg"+message);
				BigInteger Public_key=new BigInteger(Dbconnect.getPublicKey(serverids[1]));
				BigInteger n = pkg.getn();
				
				
				Encrypt encrypt1 = new Encrypt(serverids[1],message,n,Public_key); 				
				byte[] test=encrypt1.encryption();				
			
				
				System.out.println("after encryption "+test);
				
				// upload to Regional cloud
				String filepath = path + region + "\\" + userid
						+ ".txt";
				System.out.println("filepath"+filepath);
				String filenm = uid;

				User cl = new User();
				cl.setFilenm(filenm);
				cl.setFilepath(filepath);
				cl.setId(userid);
				cl.setRegion(region);
				cl.setUsage(usage);
				cl.setDate(new Date());
				cl.setMessage(message);
				cl.setServerid(serverids[1]);
				cl.setFromdate(fromdate);
				cl.setTodate(todate);
				cl.setMeterNo(meterno);
		

				if (RegionalCloud.IBEDecryption(cl, test)) {
										
					//JOptionPane.showMessageDialog(null, "Data Sent to server");					
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('done');");
					out.println("location='Login.jsp';");
					out.println("</script>");	
					//JOptionPane.showMessageDialog(null, "Data Sent to server");
					
				}

			} else if (Algo.equals("IBS")) {
				System.out.println("IBS algorithm");			

				// Sign user data
				
				AsymmetricCipherKeyPair keyPair = IBS.setup(IBS.createParameters(userid,256));
				
				CipherParameters secretKey = IBS.extract(keyPair, Integer.toString(userid));
				byte[] signature = IBS.sign(message, secretKey);

				String filepath = filenm = path + "\\" + region + "\\" + uid
						+ ".txt";
				
				// send to regional cloud
				User cl = new User();
				cl.setFilenm(filenm);
				cl.setFilepath(filepath);
				cl.setId(userid);
				cl.setRegion(region);
				cl.setUsage(usage);
				cl.setDate(new Date());
				cl.setFromdate(fromdate);
				cl.setTodate(todate);
				cl.setMeterNo(meterno);
				
				if (RegionalCloud.IBSverify( keyPair,signature, cl, message)) {
					
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Your Data Saved successfully');");
					out.println("location='Welcome.jsp';");
					out.println("</script>");	
					JOptionPane.showMessageDialog(null, "Data Sent to server");

				} else {
					System.out.println("not able to send to cloud");
				}

			}

			response.sendRedirect("Welcome.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}
