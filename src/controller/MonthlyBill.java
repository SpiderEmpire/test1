package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Grayscale;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.pageelements.Label;
import com.cete.dynamicpdf.pageelements.Row2;
import com.cete.dynamicpdf.pageelements.Table2;

import dal.Dbconnect;

@WebServlet("/MonthlyBill")
public class MonthlyBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ServletOutputStream sOut;
	Connection connection;
	String userid = "";
	static ResultSet data;
	String month, year;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		connection = Dbconnect.sqlconn();
		
		month = req.getParameter("month");
		year = req.getParameter("year");		
	
		// Create a document and set it's properties
		Document objDocument = new Document();
		objDocument.setCreator("Report.java");
		objDocument.setAuthor("Smart Grid");
		objDocument.setTitle("Report");
		String username = null;
		HttpSession session = req.getSession(false);
		if (session != null) {
			// uid = (int) session.getAttribute("uid");
			userid = (String) session.getAttribute("uid");
			username = (String) session.getAttribute("username");
		}

		String[] udata = Dbconnect.getUserDetails(username);
		String[] billdata = Dbconnect.getBillDetails(month, year, userid);
		boolean flag = true;
		if (billdata[0] == null) {
			flag = false;
		}
		
		System.out.println("hiiiii"+billdata[0]);
		
		if (flag) {
			sOut = res.getOutputStream();
			
			Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
					54.0f);

			// Create a Label to add to the page
			String strText = "Report \n\n\n\n";
			Label objLabel = new Label(strText, 0, 0, 504, 100,
					Font.getHelvetica(), 18, TextAlign.CENTER);
			// Add label to page
			objPage.getElements().add(objLabel);

			Table2 usertable = new Table2(0, 50, 512, 676, Font.getHelvetica(), 12);
			buildusertable(udata, usertable);
			objPage.getElements().add(usertable);

			Table2 metertable = new Table2(0, 200, 512, 676, Font.getHelvetica(),
					12);
		
			
			buildmetertable(billdata, metertable);
			objPage.getElements().add(metertable);

			Table2 billtable = new Table2(250, 200, 450, 676, Font.getHelvetica(),
					12);
			// String[] billdata=Dbconnect.getBillDetails(month,year,userid);
			buildbilltable(billdata, billtable);
			objPage.getElements().add(billtable);
			// Add page to document
			objDocument.getPages().add(objPage);
			// Outputs the document to current web page.
			objDocument.drawToWeb(req, res, sOut, "Report.pdf");
			sOut.close();
		}
		else
		{
					
			PrintWriter out = res.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No User records found.Please Enter data first');");
			out.println("location='Welcome.jsp';");
			out.println("</script>");
		}
	}

	void buildusertable(String[] udata, Table2 usertable) {
		usertable.getColumns().add(100);
		usertable.getColumns().add(150);
		usertable.getColumns().add(100);
		usertable.getColumns().add(150);

		try {
			Row2 row = usertable.getRows().add(20);
			row.getCellDefault().setAlign(TextAlign.CENTER);
			row.getCells().add("Name", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row.getCells().add(udata[1]);

			row.getCells().add("Bill For", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row.getCells().add(month + "-" + year);

			Row2 row1 = usertable.getRows().add(20);
			row1.getCellDefault().setAlign(TextAlign.CENTER);
			row1.getCells().add("Region", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row1.getCells().add(udata[2]);

			row1.getCells().add("Consumer Number", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row1.getCells().add(udata[11]);

			Row2 row2 = usertable.getRows().add(20);
			row2.getCellDefault().setAlign(TextAlign.CENTER);
			row2.getCells().add("Address", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row2.getCells().add(udata[4]);

			row2.getCells().add("Meter number ", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			row2.getCells().add(udata[3]);

		} catch (Exception ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}
	}

	void buildmetertable(String[] billdata, Table2 metertable) {
		// TODO Auto-generated method stub
		metertable.getColumns().add(100);
		metertable.getColumns().add(100);

		try {
			Row2 row = metertable.getRows().add(20);
			row.getCellDefault().setAlign(TextAlign.CENTER);
			row.getCells().add("Category");
			row.getCells().add("LT 1 Res 1-Phase");

			Row2 row1 = metertable.getRows().add(20);
			row1.getCellDefault().setAlign(TextAlign.CENTER);
			row1.getCells().add("DTC");
			row1.getCells().add("4677789");

			Row2 row2 = metertable.getRows().add(20);
			row2.getCellDefault().setAlign(TextAlign.CENTER);
			row2.getCells().add("Connection Load");
			row2.getCells().add("3 KW");

			Row2 row3 = metertable.getRows().add(20);
			row3.getCellDefault().setAlign(TextAlign.CENTER);
			row3.getCells().add("Units ");
			row3.getCells().add(billdata[0]);

			Row2 row4 = metertable.getRows().add(20);
			row4.getCellDefault().setAlign(TextAlign.CENTER);
			row4.getCells().add("MF");
			row4.getCells().add("01");

			Row2 row5 = metertable.getRows().add(20);
			row5.getCellDefault().setAlign(TextAlign.CENTER);
			row5.getCells().add("Tariff");
			row5.getCells().add("01");

			Row2 row6 = metertable.getRows().add(20);
			row6.getCellDefault().setAlign(TextAlign.CENTER);
			row6.getCells().add("Sanct. Load");
			row6.getCells().add("3 KW");

		} catch (Exception ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}

	}

	void buildbilltable(String[] billdata, Table2 billtable) {
		billtable.getColumns().add(100);
		billtable.getColumns().add(150);

		try {
			Row2 row = billtable.getRows().add(20);
			row.getCellDefault().setAlign(TextAlign.CENTER);
			row.getCells().add("");
			row.getCells().add("Rs.Ps");

			Row2 row1 = billtable.getRows().add(20);
			row1.getCellDefault().setAlign(TextAlign.CENTER);
			row1.getCells().add("Fixed Charges ");
			row1.getCells().add("55.00");

			Row2 row2 = billtable.getRows().add(20);
			row2.getCellDefault().setAlign(TextAlign.CENTER);
			row2.getCells().add("Energy Charges");
			int amt = Integer.parseInt(billdata[0]) * 3;
			row2.getCells().add(Integer.toString(amt));

			Row2 row3 = billtable.getRows().add(20);
			row3.getCellDefault().setAlign(TextAlign.CENTER);
			row3.getCells().add("Electricity Duty");
			row3.getCells().add("29.48");

			Row2 row4 = billtable.getRows().add(20);
			row4.getCellDefault().setAlign(TextAlign.CENTER);
			row4.getCells().add("Wheeling Chargees");
			row4.getCells().add("37.76");

			Row2 row5 = billtable.getRows().add(20);
			row5.getCellDefault().setAlign(TextAlign.CENTER);
			row5.getCells().add("Tax on sale");
			row5.getCells().add("3.00");

			Row2 row6 = billtable.getRows().add(20);
			row6.getCellDefault().setAlign(TextAlign.CENTER);
			row6.getCells().add("Other Charges");
			row6.getCells().add("0.00");

			Row2 row7 = billtable.getRows().add(20);
			row7.getCellDefault().setAlign(TextAlign.CENTER);
			row7.getCells().add("Total ", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			double total = 55 + 95.36 + 29.48 + 37.76 + amt;

			row7.getCells().add(String.valueOf(total));

		} catch (Exception ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}
	}

}
