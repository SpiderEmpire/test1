package controller;


import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Demochart extends ApplicationFrame 
{
   public Demochart( String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Threshold" ,
         "Time in Minutes" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
 
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
     
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
   
   private XYDataset createDataset( )
   {
      final XYSeries firefox = new XYSeries( "Existing system" );          
      firefox.add( 800 ,10 );          
      firefox.add( 1000 ,8.0 );  
      firefox.add( 1200 ,6.0 );  
      firefox.add( 1600 ,5.0 );  
      firefox.add( 2000 , 3.0 );          
      final XYSeries chrome = new XYSeries( "Proposed Approach" );          
      chrome.add( 800 , 7.0 );          
      chrome.add(1000 , 6.2 );   
      chrome.add(1200 , 5.1 );   
      chrome.add(1600 , 3.2 );   
      chrome.add( 2000 , 2.5 );          
            
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( firefox );          
      dataset.addSeries( chrome );          
    
      return dataset;
   }

   public static void main( String[ ] args ) 
   {
	   Demochart chart = new Demochart("", "");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}