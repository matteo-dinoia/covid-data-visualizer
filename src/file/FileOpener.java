package file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class FileOpener {
	private BufferedReader fileReader;
	
	
	//OPENING AND CLOSING
	public FileOpener(String url) {
		//File
		try {
			fileReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void close() {
		if(fileReader==null)return;
		
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean isOpenCorrectly() {
		return fileReader!=null;
	}
	
	
	//Getting row
	public String readRow() {
		String ris;
		
		try {
			ris=fileReader.readLine();
		} catch (IOException e) {
			ris=null;
		}
		return ris;
	}
	public String[] readRowSplitted(String regex) {
		String s=readRow();
		if(s==null) return null;
		
		return s.split(regex);
	}
}
