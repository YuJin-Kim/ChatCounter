package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReaderForTXT {
	
	public ArrayList<String> run(ArrayList<String> message) {
		message = filterTXTFile(message);
		message = parsingTXTFile(message);
		
		return message;
	}

	private ArrayList<String> filterTXTFile(ArrayList<String> message) {
		ArrayList<String> txtMessage = new ArrayList<String>();
		
		for (String str : message)
			if (str.startsWith("[") || str.startsWith("-"))
				txtMessage.add(str);
		
		return txtMessage;
	}
	
	private ArrayList<String> parsingTXTFile(ArrayList<String> txt) {
		ArrayList<String> parsingTXT = new ArrayList<String>();
		int year, month, day, hour, minute;
		String name, message, date="", time;
		
		for (String str : txt) {
			String pattern = "-+\\s([0-9]+).\\s([0-9]+).\\s([0-9]+).\\s...\\s-+";
			String pattern2 = "\\[(.+)\\]\\s\\[(..)\\s([0-9]+):([0-9]+)\\]\\s(.+)";
			Pattern p = Pattern.compile(pattern);
			Pattern p2 = Pattern.compile(pattern2);
			Matcher m = p.matcher(str);
			Matcher m2 = p2.matcher(str);
			
			if (m.find()) {
				year = Integer.parseInt(m.group(1));
				month = Integer.parseInt(m.group(2));
				day = Integer.parseInt(m.group(3));
				date = year + "-" + month + "-" + day;
			}
			
			if (m2.find()) {
				name = m2.group(1);
				if (m2.group(2).equals("오후") && Integer.parseInt(m2.group(3))!=12)
					hour = 12 + Integer.parseInt(m2.group(3));
				else if (m2.group(2).equals("오전") && Integer.parseInt(m2.group(3))==12)
					hour = 0;
				else
					hour = Integer.parseInt(m2.group(3));
				minute = Integer.parseInt(m2.group(4));
				message = m2.group(5);
				message = message.replaceAll("\"", "\"\"");
				time = hour + ":" + minute + ":0";
				parsingTXT.add(date + " " + time + ",\"" + name + "\",\"" + message + "\"");
			}
		}
		
		return parsingTXT;
	}
	
}
