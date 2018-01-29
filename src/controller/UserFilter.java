package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UserFilter")
public class UserFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String filter=request.getParameter("regionfilter");
		System.out.println(filter);
		
		 String URL = "ManageUsers.jsp?filter=" +filter;
         System.out.println(URL);
    	
    	 RequestDispatcher rd = request.getRequestDispatcher(URL);
	     rd.forward(request, response);

	}

}
