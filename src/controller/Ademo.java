package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Ademo")
public class Ademo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			ServletContext context = getServletContext();
			String path = context.getInitParameter("path");
			
			new File(path).mkdir();
			String region="bibevadi";
			int userid=36;
			String filename=userid+".txt";
			
			String filepath= path +  File.separator + filename;
			boolean fileflag=AmazonStorage.downloadFile(region,filename, filepath);
			System.out.println(fileflag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
