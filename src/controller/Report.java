package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.JFreeChart;

import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Grayscale;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.RgbColor;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.VAlign;
import com.cete.dynamicpdf.pageelements.Group;
import com.cete.dynamicpdf.pageelements.Label;
import com.cete.dynamicpdf.pageelements.Rectangle;
import com.cete.dynamicpdf.pageelements.Row2;
import com.cete.dynamicpdf.pageelements.Table2;
import com.cete.dynamicpdf.pageelements.charting.Chart;
import com.cete.dynamicpdf.pageelements.charting.PlotArea;
import com.cete.dynamicpdf.pageelements.charting.Title;
import com.cete.dynamicpdf.pageelements.charting.axes.IndexedXAxisLabel;
import com.cete.dynamicpdf.pageelements.charting.series.IndexedLineSeries;

import dal.Dbconnect;

@WebServlet("/Report")
public class Report extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// static int uid = 30;
	JFreeChart chart;
	ServletOutputStream sOut;
	Connection connection;
	String userid = "";
	static ResultSet data;
	boolean flag=false;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		
			

		connection = Dbconnect.sqlconn();

	
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

		Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
				54.0f);

		// Create a Label to add to the page
		String strText = "Report \n\n\n\n";
		Label objLabel = new Label(strText, 0, 0, 504, 100, Font.getHelvetica(),
				 18, TextAlign.CENTER);
				// Add label to page
		objPage.getElements().add(objLabel);
		
		
		Table2 usertable = new Table2(0, 50, 512, 676, Font.getHelvetica(), 12);
		buildusertable(udata,usertable);
		objPage.getElements().add(usertable);
		
		ResultSet dt = getContactListData();
		
		try {
			if (!dt.next() ) {
				
			    System.err.println("no data");
			    flag=true;
			} else {
				dt.beforeFirst();
			Table2 table = new Table2(30, 200, 512, 676, Font.getHelvetica(), 12);
			// table.getBorder().setWidth(1f);
			// table.getCellDefault().getBorder().setWidth(1f);
			// table.setRepeatColumnHeaderCount(1);
			// table.setRepeatRowHeaderCount(1);

			// Builds the report
			buildTable(dt, table);

			objPage.getElements().add(table);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		// graph
		addLineChart(objPage.getElements(), 300, 150);
		
		if(flag){
			PrintWriter out = res.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('No User records found.Please Enter data first');");
			out.println("location='Welcome.jsp';");
			out.println("</script>");
		}
		else
		{
			sOut = res.getOutputStream();
			objDocument.getPages().add(objPage);
			
			

			// Outputs the document to current web page.
			objDocument.drawToWeb(req, res, sOut, "Report.pdf");
			sOut.close();
		}

		// Add page to document
		
	}

	// //table

	private ResultSet getContactListData() {
		// Creates a ResultSet for the report

		try {
			Statement st = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			data = st
					.executeQuery("SELECT top 10 left(DATENAME(month,fromdate),3) as month ,"
							+ " sum(MeterReading) as reading,datename(yyyy,fromdate)  as year,datepart(mm,fromdate) as monthno	"
							+ "from Data where UserID='"
							+ userid
							+ "'"
							+ "group by DATENAME(m,fromdate),datepart(mm,fromdate),datename(yyyy,fromdate)"
							+ "order by year,monthno");
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return data;
	}

	private void buildTable(ResultSet data, Table2 table) {
		createColumns(table);
		createRowHeadings(table);
		try {
			while (data.next()) {
				createRow(table, data);
			}
		} catch (SQLException ex) {
			System.err.println("cannot move ResultSet :" + ex.getMessage());
		}
	}

	private void createColumns(Table2 table) {
		table.getColumns().add(90);
		table.getColumns().add(120);

	}

	private void createRowHeadings(Table2 table) {

		Row2 row = table.getRows().add(40, Font.getTimesBold(), 12,
				Grayscale.getBlack(), Grayscale.getLightGrey());
		row.getCellDefault().setAlign(TextAlign.CENTER);
		row.getCellDefault().setVAlign(VAlign.TOP);
		row.getCells().add("Month");
		row.getCells().add("Meter Reading");

	}

	private void createRow(Table2 table, ResultSet data) {
		Row2 row = table.getRows().add(20);
		try {
			System.out.println(data.getString(1));

			row.getCells().add(data.getString(1), Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			String s1 = String.valueOf(data.getInt(2));
			row.getCells().add(s1);

		} catch (SQLException ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}
	}

	// //graph

	private void addLineChart(Group elements, float x, float y) {
		addCaptionAndRectangle(elements, " ", x, y, 225, 225);

		// Create a chart
		Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(),
				10, RgbColor.getBlack());

		// Create a plot area
		PlotArea plotArea = chart.getPrimaryPlotArea();

		// Create header titles and add it to the chart
		Title title1 = new Title(" ");
		chart.getHeaderTitles().add(title1);

		// Create a indexed line series and add values to it

		ResultSet chartdata = getContactListData();
		IndexedLineSeries lineSeries1 = new IndexedLineSeries("Usage Graph");
		int count = 0;
		float[] reading = null;
		try {
			if (chartdata.last()) {
				count = chartdata.getRow();
				chartdata.beforeFirst();
			}
			reading = new float[count];
			for (int i = 0; chartdata.next(); i++) {
				reading[i] = chartdata.getInt(2);
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		lineSeries1.getValues().add(reading);

		// Add indexed line series to the plot area
		plotArea.getSeries().add(lineSeries1);

		// Create a title and add it to the yaxis
		Title lTitle = new Title("Reading  (in watts)");
		lineSeries1.getYAxis().getTitles().add(lTitle);

		// Adding AxisLabels to the XAxis

		int i = 0;
		try {
			chartdata.beforeFirst();
			while (chartdata.next()) {
				//System.out.println(chartdata.getString(1));
				lineSeries1.getXAxis().getLabels()
						.add(new IndexedXAxisLabel(chartdata.getString(1), i));
				i++;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		chart.getLegends().getLegend(0).setVisible(false);
		elements.add(chart);
	}

	private void addCaptionAndRectangle(Group pageElements, String caption,
			float x, float y, float width, float height) {
		// Adds a rectangle and caption to the pageElements
		Rectangle rectangle = new Rectangle(x, y + 15, width, height - 15);
		Label captionLabel = new Label(caption, x, y, 300, 10,
				Font.getHelveticaBold(), 10);
		pageElements.add(rectangle);
		pageElements.add(captionLabel);
	}
	
	void buildusertable(String[] udata,Table2 usertable)
	{
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
			
			row.getCells().add("Meter Number", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);		
			row.getCells().add(udata[3]);
			
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
			
			row2.getCells().add("Email ", Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);		
			row2.getCells().add(udata[5]);

			
		} catch (Exception ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}
	}

}
