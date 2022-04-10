package frontend;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import org.jfree.data.time.*;

import plot.GraphDataSet;
import plot.PlotDateValue;


public class App  extends JFrame implements ActionListener{
	private static final long serialVersionUID = 3572038579326256150L;
	
	private PlotDateValue plot;
	private ArrayList<JCheckBox> itemsMenu=new ArrayList<>();
 	private ArrayList<GraphDataSet<RegularTimePeriod, Double>> dataSets=GraphDataSet.getListsDateValueByCSV(
			"https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/dati-andamento-nazionale/dpc-covid19-ita-andamento-nazionale.csv",
			"yyyy-MM-dd'T'HH:mm:ss"); //2020-02-24T18:00:00

	//CONSTRUCTOR
	public static void main(String[] args) {
		new App().setVisible(true);
	}
	public App() {
		//graph
		plot=new  PlotDateValue(dataSets);
		this.add(plot.getChartPanel());
		
		//frame
		this.createMenuVisibility();
		this.setParams();
	}
	
	
	//GRAPHICS
	private void setParams() {
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void createMenuVisibility() {
		//menu visibility
		JMenu visibilityMenu=new JMenu("Visibility");
		this.setJMenuBar(new JMenuBar());
		this.getJMenuBar().add(visibilityMenu);
		
		
		//Add and remove all
		JMenuItem addAll=new JMenuItem("Add all");
		JMenuItem removeAll=new JMenuItem("Remove all");
		//--
		addAll.setActionCommand("addAll");
		removeAll.setActionCommand("removeAll");
		visibilityMenu.add(addAll);
		visibilityMenu.add(removeAll);
		addAll.addActionListener(this);
		removeAll.addActionListener(this);
		
		
		//Single visibility
		for(int i=0; i<dataSets.size(); i++) {
			JCheckBox item=new JCheckBox(dataSets.get(i).getLegend());
			item.setSelected(true);
			item.addActionListener(this);
			
			itemsMenu.add(item);
			visibilityMenu.add(item);
			item.addActionListener(this);
		}
		
		
	}
	
	
	
	//LISTENER
	@Override public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		
		if(command.equals("addAll")||command.equals("removeAll")) {
			boolean ris=command.equals("addAll");
			
			for(int i=0; i<itemsMenu.size(); i++) {
				itemsMenu.get(i).setSelected(ris);
			}
		}
		
		for(int i=0; i<itemsMenu.size(); i++) {
			boolean ris=itemsMenu.get(i).isSelected();
			plot.setVisible(i, ris);
		}
	}

}
