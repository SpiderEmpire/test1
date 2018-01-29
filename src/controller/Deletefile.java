package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.Dbconnect;

@WebServlet("/Deletefile")
public class Deletefile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uid = request.getParameter("uid");

		System.out.println("uid" + uid);
		int fileid = Integer.parseInt(request.getParameter("fileid"));
		String filenm = request.getParameter("filenm");
		String region = request.getParameter("region");

		if (AmazonStorage.deleteFile(filenm, region)) {
			if (Dbconnect.deleteFile(filenm, region, Integer.parseInt(uid))) {
				PrintWriter out = response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('File Deleted..');");
				out.println("location='Personal.jsp';");
				out.println("</script>");
			}
		}

		else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Failed to Delete.Please try again later...');");
			out.println("location='Personal.jsp';");
			out.println("</script>");
		}

		System.out.println("in delete");
	}

}
