package controller;

import com.cete.dynamicpdf.*;
import com.cete.dynamicpdf.pageelements.*;
import com.cete.dynamicpdf.pageelements.charting.*;
import com.cete.dynamicpdf.pageelements.charting.axes.*;
import com.cete.dynamicpdf.pageelements.charting.series.*;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Charting")
public class Charting  extends HttpServlet {
    
    ServletOutputStream sOut;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws IOException,ServletException {
        
        sOut = res.getOutputStream();
        // Create a document and set it's properties
        Document objDocument = new Document();
        objDocument.setCreator("Charting.java");
        Page objPage = new Page();
        addCharts(objDocument);
        
        // Outputs the document to current web page.
        objDocument.drawToWeb(req, res , sOut, "Charting.pdf");
        sOut.close();
    }
    
    private void addCharts(Document document) {
        // Create a Template and assign it to the document
        Template documentTemplate = new Template();
        document.setTemplate(documentTemplate);
        documentTemplate.getElements().add(new PageNumberingLabel(
                "Page %%CP%% of %%TP%%", 100,525, 512, 12, Font.getHelvetica(),
                12, TextAlign.CENTER));
        
        // Create a Page
        Page page1 = new Page(PageSize.LETTER, PageOrientation.LANDSCAPE,35);
        Page page2 = new Page(PageSize.LETTER, PageOrientation.LANDSCAPE);
        
        // Adds charts to the Page
        addAreaChart(page1.getElements(), 0, 40);
	  addPieChart(page1.getElements(), 0, 280);
        addLineChart(page1.getElements(),250, 40);        
        addBarChart(page1.getElements(), 250, 280);
        addColumnChart(page1.getElements(), 500, 40);
        addXYScatterChart(page1.getElements(), 500, 280);
        addMultiTypeSeriesChart(page2.getElements(), 20, 50);
        
        // Add Pages to the document
        document.getPages().add(page2);
        document.getPages().add(page1);
    }
    
    private void addAreaChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "Stacked Area Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200,Font.getHelvetica(),10,RgbColor.getBlack());
        
        // Create a plot area
        PlotArea plotArea = chart.getPrimaryPlotArea();
        
        // Create header titles and add it to the chart
        Title title1 = new Title("Website Visitors");
        chart.getHeaderTitles().add(title1);
        
        // Create indexed stacked area series elements and add values to it
        IndexedStackedAreaSeriesElement seriesElement1 = new IndexedStackedAreaSeriesElement("Website A");
        seriesElement1.getValues().add(new float[] { 5, 7, 9, 6 });
        IndexedStackedAreaSeriesElement seriesElement2 = new IndexedStackedAreaSeriesElement("Website B");
        seriesElement2.getValues().add(new float[] { 4, 2, 5, 8 });
        IndexedStackedAreaSeriesElement seriesElement3 = new IndexedStackedAreaSeriesElement("Website C");
        seriesElement3.getValues().add(new float[] { 2, 4, 6, 9 });
        
        // Create autogradient and add it to the series
        AutoGradient autogradient1 = new AutoGradient(90f, CmykColor.getRed(), CmykColor.getIndianRed());
        seriesElement1.setColor(autogradient1);
        AutoGradient autogradient2 = new AutoGradient(90f, CmykColor.getGreen(), CmykColor.getYellowGreen());
        seriesElement2.setColor(autogradient2);
        AutoGradient autogradient3 = new AutoGradient(120f, CmykColor.getBlue(), CmykColor.getLightBlue());
        seriesElement3.setColor(autogradient3);
        
        // Create a Indexed Stacked Area Series
        IndexedStackedAreaSeries areaSeries = new IndexedStackedAreaSeries();
        
        // Add indexed stacked area series elements to the Indexed Stacked Area Series
        areaSeries.add(seriesElement1);
        areaSeries.add(seriesElement2);
        areaSeries.add(seriesElement3);
        
        // Add series to the plot area
        plotArea.getSeries().add(areaSeries);
        
        // Create a title and add it to the YAxis
        Title lTitle = new Title("Visitors (in millions)");
        areaSeries.getYAxis().getTitles().add(lTitle);
        
        //Adding AxisLabels to the XAxis
        areaSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q1", 0));
        areaSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q2", 1));
        areaSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q3", 2));
        areaSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q4", 3));
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addLineChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "Line Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(), 10, RgbColor.getBlack());
        
        // Create a plot area
        PlotArea plotArea = chart.getPrimaryPlotArea();
        
        // Create header titles and add it to the chart
        Title title1 = new Title("Website Visitors");
        chart.getHeaderTitles().add(title1);
        
        // Create a indexed line series and add values to it
        IndexedLineSeries lineSeries1 = new IndexedLineSeries("Website A");
        lineSeries1.getValues().add(new float[] { 5, 7, 9, 6 });
        IndexedLineSeries lineSeries2 = new IndexedLineSeries("Website B");
        lineSeries2.getValues().add(new float[] { 4, 2, 5, 8 });
        IndexedLineSeries lineSeries3 = new IndexedLineSeries("Website C");
        lineSeries3.getValues().add(new float[] { 2, 4, 6, 9 });
        
        // Add indexed line series to the plot area
        plotArea.getSeries().add(lineSeries1);
        plotArea.getSeries().add(lineSeries2);
        plotArea.getSeries().add(lineSeries3);
        
        // Create a title and add it to the yaxis
        Title lTitle = new Title("Visitors (in millions)");
        lineSeries1.getYAxis().getTitles().add(lTitle);
        
        //Adding AxisLabels to the XAxis
        lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q1", 0));
        lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q2", 1));
        lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q3", 2));
        lineSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q4", 3));
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addPieChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "Pie Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(), 10, RgbColor.getBlack());
        
        // Add a plot area to the chart
        PlotArea plotArea = chart.getPlotAreas().add(50, 50, 300, 300);
        
        // Create the Header titles and add it to the chart
        Title tTitle = new Title("Website Visitors (in millions)");
        chart.getHeaderTitles().add(tTitle);
        
        // Create autogradient and add it to the series
        AutoGradient autogradient1 = new AutoGradient(90f, CmykColor.getRed(), CmykColor.getIndianRed());
        AutoGradient autogradient2 = new AutoGradient(90f, CmykColor.getGreen(), CmykColor.getYellowGreen());
        AutoGradient autogradient3 = new AutoGradient(90f, CmykColor.getBlue(), CmykColor.getLightBlue());
        
        // Create a scalar datalabel
        ScalarDataLabel da = new ScalarDataLabel(true, false, false);
        
        // Create a pie series
        PieSeries pieSeries = new PieSeries();
        
        // Set scalar datalabel to the pie series
        pieSeries.setDataLabel(da);
        
        // Add series to the plot area
        plotArea.getSeries().add(pieSeries);
        
        //Add pie series elements to the pie series
        pieSeries.getElements().add(27, "Website A");
        pieSeries.getElements().add(19, "Website B");
        pieSeries.getElements().add(21, "Website C");
        
        // Assign autogradient colors to series elements
        pieSeries.getElements().getElement(0).setColor(autogradient1);
        pieSeries.getElements().getElement(1).setColor(autogradient2);
        pieSeries.getElements().getElement(2).setColor(autogradient3);
        
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addBarChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "Bar Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(), 10, RgbColor.getBlack());
        
        // Create a plot area
        PlotArea plotArea = chart.getPrimaryPlotArea();
        // Create header titles and add it to the chart
        
        Title title1 = new Title("Website Visitors");
        chart.getHeaderTitles().add(title1);
        
        // Create a indexed bar series and add values to it
        IndexedBarSeries barSeries1 = new IndexedBarSeries("Website A");
        barSeries1.getValues().add(new float[] { 5, 7, 9, 6 });
        IndexedBarSeries barSeries2 = new IndexedBarSeries("Website B");
        barSeries2.getValues().add(new float[] { 4, 2, 5, 8 });
        IndexedBarSeries barSeries3 = new IndexedBarSeries("Website C");
        barSeries3.getValues().add(new float[] { 2, 4, 6, 9 });
        
        // Create autogradient and add it to the series
        AutoGradient autogradient1 = new AutoGradient(180f, CmykColor.getRed(), CmykColor.getIndianRed());
        barSeries1.setColor(autogradient1);
        AutoGradient autogradient2 = new AutoGradient(180f, CmykColor.getGreen(), CmykColor.getYellowGreen());
        barSeries2.setColor(autogradient2);
        AutoGradient autogradient3 = new AutoGradient(180f, CmykColor.getBlue(), CmykColor.getLightBlue());
        barSeries3.setColor(autogradient3);
        
        // Add indexed bar series to the plot area
        plotArea.getSeries().add(barSeries1);
        plotArea.getSeries().add(barSeries2);
        plotArea.getSeries().add(barSeries3);
        
        // Create a title and add it to the xaxis
        Title lTitle = new Title("Visitors (in millions)");
        barSeries1.getXAxis().getTitles().add(lTitle);
        
        //Adding AxisLabels to the yAxis
        barSeries1.getYAxis().getLabels().add(new IndexedYAxisLabel("Q1", 0));
        barSeries1.getYAxis().getLabels().add(new IndexedYAxisLabel("Q2", 1));
        barSeries1.getYAxis().getLabels().add(new IndexedYAxisLabel("Q3", 2));
        barSeries1.getYAxis().getLabels().add(new IndexedYAxisLabel("Q4", 3));
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addColumnChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "100% Stacked Column Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(), 10, RgbColor.getBlack());
        
        // Create a plot area
        PlotArea plotArea = chart.getPrimaryPlotArea();
        
        // Create header titles and add it to the chart
        Title title1 = new Title("Website Visitors");
        chart.getHeaderTitles().add(title1);
        
        // Create a indexed 100% column series elements and add values to it
        Indexed100PercentStackedColumnSeriesElement seriesElement1 = new Indexed100PercentStackedColumnSeriesElement("Website A");
        seriesElement1.getValues().add(new float[] { 5, 7, 9, 6 });
        Indexed100PercentStackedColumnSeriesElement seriesElement2 = new Indexed100PercentStackedColumnSeriesElement("Website B");
        seriesElement2.getValues().add(new float[] { 4, 2, 5, 8 });
        Indexed100PercentStackedColumnSeriesElement seriesElement3 = new Indexed100PercentStackedColumnSeriesElement("Website C");
        seriesElement3.getValues().add(new float[] { 2, 4, 6, 9 });
        
         // Create autogradient and add it to the series
        AutoGradient autogradient1 = new AutoGradient(90f, CmykColor.getRed(), CmykColor.getIndianRed());
        seriesElement1.setColor(autogradient1);
        AutoGradient autogradient2 = new AutoGradient(90f, CmykColor.getGreen(), CmykColor.getYellowGreen());
        seriesElement2.setColor(autogradient2);
        AutoGradient autogradient3 = new AutoGradient(120f, CmykColor.getBlue(), CmykColor.getLightBlue());
        seriesElement3.setColor(autogradient3);
        
        // Create a Indexed 100% Stacked Column Series
        Indexed100PercentStackedColumnSeries columnSeries = new Indexed100PercentStackedColumnSeries();
        
        // Add indexed 100% column series elements to the Indexed 100% Stacked Column Series
        columnSeries.add(seriesElement1);
        columnSeries.add(seriesElement2);
        columnSeries.add(seriesElement3);
        
        // Add series to the plot area
        plotArea.getSeries().add(columnSeries);
        
        // Create a title and add it to the yaxis
        Title lTitle = new Title("Visitors (in millions)");
        columnSeries.getYAxis().getTitles().add(lTitle);
        
        //Adding AxisLabels to the XAxis
        columnSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q1", 0));
        columnSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q2", 1));
        columnSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q3", 2));
        columnSeries.getXAxis().getLabels().add(new IndexedXAxisLabel("Q4", 3));
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addXYScatterChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "XYScatter Chart", x, y, 225, 225);
        
        // Create a chart
        Chart chart = new Chart(x + 10, y + 25, 200, 200, Font.getHelvetica(), 10, RgbColor.getBlack());
        
        // Add a plot Area to the chart
        PlotArea plotArea = chart.getPrimaryPlotArea();
        
        // Create a Header title and add it to the chart
        Title tTitle = new Title("Player Height and Weight");
        chart.getHeaderTitles().add(tTitle);
        
        // Create a xyScatter series and add values to it
        XYScatterSeries xyScatterSeries1 = new XYScatterSeries("Team A");
        xyScatterSeries1.getValues().add(112, 55);
        xyScatterSeries1.getValues().add(125, 60);
        xyScatterSeries1.getValues().add(138, 68);
        xyScatterSeries1.getValues().add(150, 73);
        xyScatterSeries1.getValues().add(172, 80);
        XYScatterSeries xyScatterSeries2 = new XYScatterSeries("Team B");
        xyScatterSeries2.getValues().add(110, 54);
        xyScatterSeries2.getValues().add(128, 62);
        xyScatterSeries2.getValues().add(140, 70);
        xyScatterSeries2.getValues().add(155, 75);
        xyScatterSeries2.getValues().add(170, 80);
        
        // Add xyScatter series to the plot Area
        plotArea.getSeries().add(xyScatterSeries1);
        plotArea.getSeries().add(xyScatterSeries2);
        
        // Create axis titles and add it to the axis
        Title title1 = new Title("Height (Inches)");
        Title title2 = new Title("Weight (Pounds)");
        xyScatterSeries1.getYAxis().getTitles().add(title1);
        xyScatterSeries1.getXAxis().getTitles().add(title2);
        
        // Set XAxis min value
        xyScatterSeries1.getXAxis().setMin(50);         
              
        // Set YAxis min  value
        xyScatterSeries1.getYAxis().setMin(100);
     
        chart.getLegends().getLegend(0).setVisible(false);
        elements.add(chart);
    }
    
    private void addMultiTypeSeriesChart(Group elements, float x, float y) {
        addCaptionAndRectangle(elements, "Plot Area With Different Kinds Of Series And Multiple Axis ", x, y, 650, 410);
        
        // Create a chart
        Chart chart = new Chart(x + 25, y + 37, 600, 350);
        
        // Create a Auto gradient and set it to chart back ground color
        AutoGradient autogradient = new AutoGradient(90f, CmykColor.getLightYellow(), CmykColor.getLightSkyBlue());
        chart.setBackgroundColor(autogradient);
        
        // Create a plot area
        PlotArea plotArea = chart.getPrimaryPlotArea();
        
        // Create header titles and add it to the chart
        Title title1 = new Title("Company Sales and Website Visitors ");
        title1.setAlign(Align.LEFT);
        chart.getHeaderTitles().add(title1);
        
        // Create a indexed line series and add values to it
        IndexedLineSeries lineSeries1 = new IndexedLineSeries("Website A Visitors");
        lineSeries1.getValues().add(new float[] { 1.5f, 8, 7.5f, 5.5f });
        lineSeries1.setColor(RgbColor.getDarkBlue());
        
        IndexedLineSeries lineSeries2 = new IndexedLineSeries("Website B Visitors");
        lineSeries2.setColor(RgbColor.getLimeGreen());
        lineSeries2.getValues().add(new float[] { 4, 3, 7, 7.5f });
        
        // Create markers and add it to the series
        Marker marker1 = Marker.getTriangle(7);
        lineSeries1.setMarker(marker1);
        Marker marker2 = Marker.getCircle(7);
        lineSeries2.setMarker(marker2);
        
        // Add indexed line series to the plot area
        plotArea.getSeries().add(lineSeries1);
        plotArea.getSeries().add(lineSeries2);
        
        // Create a NumericYAxis and a title to it
        NumericYAxis numericyaxis1 = new NumericYAxis();
        numericyaxis1.setAnchorType(YAxisAnchorType.RIGHT);
        numericyaxis1.getTitles().add(new Title("Sales (in $ millions)"));
        numericyaxis1.setInterval(1);
        
        // Create a indexed column series and add values to it
        IndexedColumnSeries columnSeries1 = new IndexedColumnSeries("Company A Sales", numericyaxis1);
        columnSeries1.getValues().add(new float[] { 2, 10, 14, 17 });
        columnSeries1.setColor(RgbColor.getBlue());
        IndexedColumnSeries columnSeries2 = new IndexedColumnSeries("Company B Sales", numericyaxis1);
        columnSeries2.setColor(RgbColor.getLime());
        columnSeries2.getValues().add(new float[] { 7, 4, 10, 15 });
        
        // Create a Value position data label
        BarColumnValuePositionDataLabel valuepositiondatalabel = new BarColumnValuePositionDataLabel(  true,true,false  );
        columnSeries1.setDataLabel(valuepositiondatalabel);
        valuepositiondatalabel.setFontSize(7);
        columnSeries1.getDataLabel().setPrefix("(");
        columnSeries1.getDataLabel().setSuffix(")");
        columnSeries2.setDataLabel(valuepositiondatalabel);
        
        // Add indexed column series to the plot area
        plotArea.getSeries().add(columnSeries1);
        plotArea.getSeries().add(columnSeries2);
        YAxisGridLines minorGridLines = new YAxisGridLines();
        minorGridLines.setLineStyle(LineStyle.getDots());
        
        plotArea.getYAxes().getDefaultNumericAxis().setMajorGridLines(new YAxisGridLines());
        plotArea.getYAxes().getDefaultNumericAxis().setMinorGridLines(new YAxisGridLines());
        plotArea.getXAxes().getDefaultIndexedAxis().setMajorGridLines(new XAxisGridLines());
        plotArea.getYAxes().getDefaultNumericAxis().setMinorTickMarks(new YAxisTickMarks());
        plotArea.getYAxes().getDefaultNumericAxis().setMajorTickMarks(new YAxisTickMarks());
        
        // Add title to Yaxis
        lineSeries1.getYAxis().getTitles().add(new Title("Visitors (in millions)"));
        
        //Adding AxisLabels to the XAxis
        columnSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q1", 0));
        columnSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q2", 1));
        columnSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q3", 2));
        columnSeries1.getXAxis().getLabels().add(new IndexedXAxisLabel("Q4", 3));
        chart.getLegends().getLegend(0).setLineStyle(LineStyle.getDots());
        chart.getLegends().getLegend(0).setBorderColor(RgbColor.getBlack());
        chart.getLegends().getLegend(0).setBackgroundColor(CmykColor.getLavender());
        elements.add(chart);
    }
    
    private void addCaptionAndRectangle(Group pageElements, String caption, float x, float y, float width, float height) {
        // Adds a rectangle and caption to the pageElements
        Rectangle rectangle = new Rectangle(x, y + 15, width, height - 15);
        Label captionLabel = new Label(caption, x, y, 300, 10, Font.getHelveticaBold(), 10);
        pageElements.add(rectangle);
        pageElements.add(captionLabel);
    }
}
