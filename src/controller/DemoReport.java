package controller;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.text.Document;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import services.ReportDetails;

import com.itextpdf.text.pdf.DefaultFontMapper;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import dal.Dbconnect;

public class DemoReport {

	public static void main(String[] args) {
		String from="2017-01-20";
		String to="2017-01-31";
		JFreeChart chart = null;
		try {			
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();					
			
			List<ReportDetails> report = Dbconnect.getReportData(from, to, 24);
			for (ReportDetails r : report) {
				System.out.println(r.getUsage() + r.getDate());
				dataset.addValue(r.getUsage(), "user usage", r.getDate());
			}

		
			/* create chart */
			 chart = ChartFactory.createLineChart("Report", "days",
					"Reading in watt", dataset, PlotOrientation.VERTICAL,
					false, true, false);

			
		} catch (Exception e) {
			System.err.println(e.toString()); /* Throw exceptions to log files */
		}
		
		
		PdfWriter writer = null;

		Document document=(Document) new com.itextpdf.text.Document();

		try {
			writer = PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(
					"E:\\report.pdf"));
			((PdfWriter) document).open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(100, 100);
			Graphics2D graphics2d = template.createGraphics(100, 100,
					new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, 100,
					100);

			chart.draw(graphics2d, rectangle2d);
			
			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
