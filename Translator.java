package ru.sixteenfourteen.translator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Translator {
	URL url = null;
	BufferedReader reader = null;
	URLConnection urlConnection;
	final static String adress = "http://translate.yandex.ru/tr.json/translate?lang=en-ru&text=";

	public String getTranslate(String englishString) throws Exception {
		String russianString = "";
		try {
			englishString = spaceToPlus(englishString);
			url = new URL(adress + englishString);
			urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(20000);
			urlConnection.connect();
			reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			russianString = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		russianString = russianString.substring(1, russianString.length() - 1);
		return russianString;
	}

	String spaceToPlus(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				str = str.substring(0, i) + '+'
						+ str.substring(i + 1, str.length());
			}
		}
		return str;
	}
}
