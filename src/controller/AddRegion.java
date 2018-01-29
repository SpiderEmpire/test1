package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dal.Dbconnect;
import services.Region;

@WebServlet("/AddRegion")
public class AddRegion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();


		Boolean flag = false;
		String rn = request.getParameter("txtregion");
		String region=rn.toLowerCase();
		String DC = request.getParameter("txtDC");
		String cost = request.getParameter("txtcost");
		String Snm = request.getParameter("txtSnm");
		String sdesc = request.getParameter("txtSdesc");

		Region r = new Region();
		r.setCost(cost);
		r.setDCnm(DC);
		r.setRname(region);
		r.setSdesc(sdesc);
		r.setServernm(Snm);
		
		if(AmazonStorage.createFolder(region))
		{
			if( Dbconnect.addRegion(r))
			{
				ServletContext context = getServletContext();
				String path = context.getInitParameter("path");
				new File(path).mkdir();
				new File(path+region).mkdirs();
				// JOptionPane.showMessageDialog(null, "Region Added to cloud");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Region created to Cloud');");
				out.println("location='ManageRegion.jsp';");
				out.println("</script>");
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Enter correct details...');");
				out.println("location='AddRegion.jsp';");
				out.println("</script>");
			}
		}	
		 else {

			out.println("<script type=\"text/javascript\">");
			out.println("alert('Enter correct details or region already exist');");
			out.println("location='AddRegion.jsp';");
			out.println("</script>");
		}
	}
}
