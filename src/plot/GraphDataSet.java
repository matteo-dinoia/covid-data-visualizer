package plot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.jfree.data.time.*;

import file.FileOpener;


public class GraphDataSet<K, V> {
	
	private String legend;
	private ArrayList<GraphData<K, V>> datas=new ArrayList<>();
	
	
	//COSTRUCTOR
	private GraphDataSet(String legend) {
		this.setLegend(legend);
	}
	public static ArrayList<GraphDataSet<RegularTimePeriod, Double>> getListsDateValueByCSV(String url, String dateFormat) {
		ArrayList<GraphDataSet<RegularTimePeriod, Double>> ris=new ArrayList<>();
		
		FileOpener file=new FileOpener(url);
		if(!file.isOpenCorrectly()) return null;
		
	
		//Title
		String[] titles=file.readRowSplitted(",");
		for(int i=1; i<titles.length; i++) {
			ris.add(new GraphDataSet<RegularTimePeriod, Double>(titles[i]));
		}
		
		//Data
		SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
		String[] row=file.readRowSplitted(",");
		while(row!=null){
			Day date=new Day(parseDate(sdf,row[0]));
			for(int i=1; i<row.length&&i<ris.size(); i++) {
				ris.get(i).addDataNotNull(date, parseDouble(row[i]));
			}
			row=file.readRowSplitted(",");
		}
		
		
		//Closure
		file.close();
		
		return ris;
	}
	private static Date parseDate(SimpleDateFormat sdf, String s) {
		Date ris;
		
		try {
			ris=sdf.parse(s);
		} catch (ParseException e) {
			ris=null;
		}
		
		return ris;
	}
	private static Double parseDouble(String s) {
		Double ris;
		
		try {
			ris=Double.valueOf(s);
		} catch (Exception e) {
			ris=null;
		}
		
		return ris;
	}
	

	
	//GETTER AND SETTER
	public GraphData<K, V> getData(int index) {
		return datas.get(index);
	}
	public void setData(int index, GraphData<K, V> data) {
		datas.set(index, data);
	}
	public void addDataNotNull(GraphData<K, V> data) {
		if(data==null) return;
		datas.add(data);
	}
	public void addDataNotNull(K key, V value) {
		if(key==null|| value==null) return;
		addDataNotNull(new GraphData<K,V>(key, value));
	}
	public String getLegend() {
		return legend;
	}
	public void setLegend(String legend) {
		this.legend = legend;
	}
	
	
	//UTILS
	public int size() {
		return datas.size();
	}


}
