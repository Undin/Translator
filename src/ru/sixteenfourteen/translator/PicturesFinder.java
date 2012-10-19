package ru.sixteenfourteen.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class PicturesFinder {
	
	final static String firstPartOfAdress = "http://ajax.googleapis.com/ajax/services/search/images?v=1.0&imgsz=small|medium&rsz=8&q=";
	final static String str = "\"url\":\"";
	String adress;	
	StringBuilder pageCode;
	URL pageURL;
	URLConnection connection;
	BufferedReader br;
	int counter = 0;

	public void setTranslatingWord(String word) {
		adress = firstPartOfAdress + word + "&start=";
	}
	
	public String nextURL() {
		if (counter % 8 == 0) {
			pageCode = new StringBuilder();			
			try {
				pageURL = new URL(adress + counter);
				connection = pageURL.openConnection();
				connection.setConnectTimeout(20000);
				connection.connect();
				br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF8"));
			} catch (Exception e) {
				counter += 8;
				return null;
			}
			String nextLine;
			try {
				while ((nextLine = br.readLine()) != null) {
					pageCode.append(nextLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		int index = pageCode.indexOf(str);
		if (index == -1) {
			counter++;
			return null;
		}
		else {
			counter++;
			pageCode = pageCode.delete(0, index + str.length());
			return  pageCode.substring(0, pageCode.indexOf("\""));
		}
	}
}