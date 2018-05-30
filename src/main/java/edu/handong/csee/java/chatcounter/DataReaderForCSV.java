package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReaderForCSV {
	
	public ArrayList<String> run(ArrayList<String> message) {
		message = filterCSVFile(message);
		message = parsingCSVFile(message);
		
		return message;
	}

	private ArrayList<String> filterCSVFile(ArrayList<String> message) {
		ArrayList<String> csvMessage = new ArrayList<String>();
		
		for (String str : message)
			if (str.startsWith("2018"))
				csvMessage.add(str);
		
		return csvMessage;
	}

	private ArrayList<String> parsingCSVFile(ArrayList<String> cvs) {
		ArrayList<String> parsingCVS = new ArrayList<String>();
		int year, month, day, hour, minute, second;
		String name, message, date;
		
		for (String str : cvs) {
			String pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):([0-9]+)\\,\\\"(.+)\\\"\\,\\\"(.*[^\\\"])";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(str);
			
			if (m.find()) {
				year = Integer.parseInt(m.group(1));
				month = Integer.parseInt(m.group(2));
				day = Integer.parseInt(m.group(3));
				hour = Integer.parseInt(m.group(4));
				minute = Integer.parseInt(m.group(5));
				second = Integer.parseInt(m.group(6));
				name = m.group(7);
				if (m.group(8).equals("Photo"))
					message = "사진";
				else
					message = m.group(8);
				date = year + "-" + month + "-" + day;
				parsingCVS.add(date + " " + hour + ":" + minute + ":" + second + ",\"" + name + "\",\"" + message + "\"");
			}
			
//			pattern = "([0-9]+)-([0-9]+)-([0-9]+)\\s([0-9]+):([0-9]+):([0-9]+)\\,\\\"(.+)\\\"\\,\\\"(.+)";
//			
//			if (m.find()) {
//				year = Integer.parseInt(m.group(1));
//				month = Integer.parseInt(m.group(2));
//				day = Integer.parseInt(m.group(3));
//				hour = Integer.parseInt(m.group(4));
//				minute = Integer.parseInt(m.group(5));
//				second = Integer.parseInt(m.group(6));
//				name = m.group(7);
//				if (m.group(8).equals("Photo"))
//					message = "사진";
//				else
//					message = m.group(8);
//				date = year + "-" + month + "-" + day;
//				parsingCVS.add(date + " " + hour + ":" + minute + ":" + second + ",\"" + name + "\"," + message);
//			}
		}
		
		return parsingCVS;
	}
	
}
