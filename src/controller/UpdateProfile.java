package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dal.Dbconnect;
import services.User;


@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int uid=0;
		HttpSession session = request.getSession(false);
		if (session != null) {
			uid =  Integer.parseInt((String) session.getAttribute("uid"));
			
		}
	
		
		
		String fnm = request.getParameter("txtfnm");
		String lnm = request.getParameter("txtlnm");
		String email = request.getParameter("txtemail");
		String phone = request.getParameter("txtphone");
		String pwd = request.getParameter("txtpassword");
		String meterNo = request.getParameter("txtmeterno");
		String region = request.getParameter("region");		
		String address = request.getParameter("address");
		
		
		User cl = new User();

		cl.setFnm(fnm);
		cl.setLnm(lnm);	
		cl.setEmail(email);
		cl.setPhone(phone);
		cl.setPwd(pwd);		
		cl.setMeterNo(meterNo);
		cl.setRegion(region);
		cl.setAddress(address);
		cl.setUId(uid);
		boolean flag = Dbconnect.updateprofile(cl);

		if (flag) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Updated Successfully');");
			out.println("location='Profile.jsp';");
			out.println("</script>");
			
		}
		else
		{
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert(' please enter all correct details');");
			out.println("location='Profile.jsp';");
			out.println("</script>");
		}

		
	}

}
