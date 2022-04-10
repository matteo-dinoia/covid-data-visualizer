package plot;


import org.jfree.chart.*;

public abstract class Plot {
	
	//CHART PANEL
	private ChartPanel chartPanel;
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	public void createChartPanel(JFreeChart chart) {
		chartPanel=new ChartPanel(chart);
	}


}
