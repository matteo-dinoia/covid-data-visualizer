package plot;

import java.util.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.*;

public class PlotDateValue extends Plot{
	
	private ArrayList<GraphDataSet<RegularTimePeriod, Double>> dataSets;
	private XYItemRenderer renderer;
	
	
	//COSTRUCTOR
	public PlotDateValue(ArrayList<GraphDataSet<RegularTimePeriod, Double>> dataSets) {
		this.dataSets=dataSets;
		super.createChartPanel(getChart());
	}
	protected JFreeChart getChart() {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "", "Giorno", "Casi", getDataSet());
		
		renderer=((XYPlot)chart.getPlot()).getRenderer();
		return chart;
	}
	protected TimeSeriesCollection getDataSet() {
		TimeSeriesCollection ris =new TimeSeriesCollection();
		if(dataSets==null) return ris; 
		
		for(int i=0; i<dataSets.size(); i++) {
			try {
				ris.addSeries(getSeries(dataSets.get(i)));
			}catch(Exception e) {}
		}
		
		return ris;
	}
	protected TimeSeries getSeries(GraphDataSet<RegularTimePeriod, Double> dataSet) {
		TimeSeries ris=new TimeSeries(dataSet.getLegend());
		
		for(int i=0; i<dataSet.size(); i++) {
			try {
				GraphData<RegularTimePeriod, Double> data=dataSet.getData(i);
				ris.add(data.getKey(), data.getValue());
			}catch(Exception e) {}
		}
		
		return ris;
	}
	
	
	//VISIBLE
	public void setVisible(int index, boolean value) {
		renderer.setSeriesVisible(index, value);
	}


}
