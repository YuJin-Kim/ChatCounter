package edu.handong.csee.java.chatcounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This class defines DataReaderForTXT object.</br>
 * The DataReaderForTXT has no member.</br>
 * The DataReaderForTXT has run(), filterTXTFile(), parsingTXTFile().</br>
 *  * This DataReaderForTXT class inherits DataReader class.</br>
 * 
 * @author YuJin
 *
 */

public class DataReaderForTXT extends DataReader{

	/**
	 * 
	 * This method is for running(reading txt file).</br>
	 * 
	 * @param message
	 * @return
	 */

	public ArrayList<String> run(File file) {
		ArrayList<String> message = new ArrayList<String>();

		message = getMessage(file);
		message = parsingTXTFile(message);

		return message;
	}
	
	/**
	 * 
	 * This method is for getting message from txt file.</br>
	 * 
	 * @param txt
	 * @return
	 */

	private ArrayList<String> getMessage(File txt) {
		ArrayList<String> message = new ArrayList<String>();

		try {
			FileReader fileReader = new FileReader(txt);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = "";
			while((line = bufferedReader.readLine()) != null)
				message.add(line);

			bufferedReader.close();

		}catch (FileNotFoundException e) {
		}catch (IOException e) {
			System.out.println(e);
		}

		return message;
	}

	/**
	 * 
	 * This method is for parsing txt file.</br>
	 * 
	 * @param txt
	 * @return
	 */

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
