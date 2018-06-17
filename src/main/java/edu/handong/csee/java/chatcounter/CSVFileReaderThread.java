package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVFileReaderThread implements Runnable {
	File file;
	ArrayList<String> message = new ArrayList<String>();
	
	public CSVFileReaderThread(File file) {
		this.file = file;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		getMessagesFromCSVFiles(file);
		message = parsingCSVFile(message);
	}
	
	private void getMessagesFromCSVFiles(File file) {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while((line = bufferedReader.readLine()) != null)
				message.add(line);

			bufferedReader.close();

		}catch (FileNotFoundException e) {
		}catch (IOException e) {
			System.out.println(e);
		}
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
		}

		return parsingCVS;
	}
	
	public ArrayList<String> getMessage() {
		return message;
	}

}
