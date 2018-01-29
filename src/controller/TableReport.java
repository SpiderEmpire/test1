package controller;

import com.cete.dynamicpdf.Grayscale;
import com.cete.dynamicpdf.Document;
import com.cete.dynamicpdf.Font;
import com.cete.dynamicpdf.Page;
import com.cete.dynamicpdf.PageOrientation;
import com.cete.dynamicpdf.PageSize;
import com.cete.dynamicpdf.RgbColor;
import com.cete.dynamicpdf.TextAlign;
import com.cete.dynamicpdf.pageelements.*;
import com.cete.dynamicpdf.pageelements.charting.Chart;
import com.cete.dynamicpdf.pageelements.charting.PlotArea;
import com.cete.dynamicpdf.pageelements.charting.Title;
import com.cete.dynamicpdf.pageelements.charting.axes.IndexedXAxisLabel;
import com.cete.dynamicpdf.pageelements.charting.series.IndexedLineSeries;
import com.cete.dynamicpdf.VAlign;
import dal.Dbconnect;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

@WebServlet("/TableReport")
public class TableReport extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ServletOutputStream sOut;

	Connection connection;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		// CeTeConnection ceTe =
		// (CeTeConnection)getServletContext().getAttribute("cetecon");
		connection = Dbconnect.sqlconn();
		sOut = res.getOutputStream();

		// Create a document and set it's properties
		Document objDocument = new Document();
		objDocument.setCreator("Table.java");
		objDocument.setAuthor("ceTe Software");
		objDocument.setTitle("Table Example");

		Page objPage = new Page(PageSize.LETTER, PageOrientation.PORTRAIT,
				54.0f);

		// Create a Label to add to the page
		String strText = "Report";
		Label objLabel = new Label(strText, 0, 0, 504, 100,
				Font.getHelvetica(), 18, TextAlign.CENTER);
		
		ResultSet data = getContactListData();
		Table2 table = new Table2(0, 0, 512, 676, Font.getHelvetica(), 12);
		table.getBorder().setWidth(1f);
		table.getCellDefault().getBorder().setWidth(1f);
		table.setRepeatColumnHeaderCount(1);
		table.setRepeatRowHeaderCount(1);

		// Builds the report
		buildTable(data, table);

		addTableToPage(objDocument, table, "(1, 1)");
		// addTableToPage(objDocument, table.getOverflowColumns(), "(1, 2)");
		Table2 objOverflowRowTable = table.getOverflowRows();
		// addTableToPage(objDocument, objOverflowRowTable, "(2, 1)");
		// addTableToPage(objDocument, objOverflowRowTable.getOverflowColumns(),
		// "(2, 2)");
		// Outputs the TableReport to the current web page

		Page page1 = new Page(PageSize.LETTER, PageOrientation.LANDSCAPE, 35);

		addLineChart(page1.getElements(), 250, 40);
		objDocument.drawToWeb(req, res, sOut, "Report.pdf");
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sOut.close();
	}

	private void addTableToPage(Document document, Table2 table,
			String pageLabel) {
		Page page = new Page(PageSize.LETTER);
		if (table != null) {
			page.getElements().add(table);
		}
		page.getElements().add(
				new Label(pageLabel, 0, page.getDimensions().getBody()
						.getHeight() - 12, page.getDimensions().getBody()
						.getWidth(), 12, Font.getHelvetica(), 12,
						TextAlign.CENTER));
		document.getPages().add(page);
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

			row.getCells().add(data.getString(1), Font.getHelvetica(), 12,
					Grayscale.getBlack(), Grayscale.getLightGrey(), 1);
			String s1 = String.valueOf(data.getInt(2));
			row.getCells().add(s1);

		} catch (SQLException ex) {
			System.err.println("cannot retrieve data from ResultSet :"
					+ ex.getMessage());
		}
	}

	private void createColumns(Table2 table) {
		table.getColumns().add(50);
		table.getColumns().add(150);

	}

	private ResultSet getContactListData() {
		// Creates a ResultSet for the report
		ResultSet data = null;
		try {
			Statement st = connection.createStatement();

			data = st
					.executeQuery("SELECT DATENAME(month,fromdate) as month,sum(MeterReading) as reading "
							+ "from Data where UserID=30 group by DATENAME(month,fromdate)");
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
		}
		return data;
	}

	private void addLineChart(Group elements, float x, float y) {
		addCaptionAndRectangle(elements, "Line Chart", x, y, 225, 225);

		// Create a chart
		Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(),
				10, RgbColor.getBlack());

		// Create a plot area
		PlotArea plotArea = chart.getPrimaryPlotArea();

		// Create header titles and add it to the chart
		Title title1 = new Title("Website Visitors");
		chart.getHeaderTitles().add(title1);

		// Create a indexed line series and add values to it
		IndexedLineSeries lineSeries1 = new IndexedLineSeries("Website A");
		lineSeries1.getValues().add(new float[] { 5, 7, 9, 6 });

		// Add indexed line series to the plot area
		plotArea.getSeries().add(lineSeries1);

		// Create a title and add it to the yaxis
		Title lTitle = new Title("Visitors (in millions)");
		lineSeries1.getYAxis().getTitles().add(lTitle);

		// Adding AxisLabels to the XAxis

		lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q1", 0));
		lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q2", 1));
		lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q3", 2));
		lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q4", 3));
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

}
