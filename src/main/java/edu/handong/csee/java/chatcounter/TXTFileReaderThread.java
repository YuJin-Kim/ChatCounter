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
 * This class defines TXTFileReaderThread object.<br>
 * The TXTFileReaderThread has file and message members.</br>
 * The TXTFileReaderThread has its constructor, run(), getMessageFromTXTFiles, pasringTXTFile() and getMessage(
 * ) methods.</br>
 * This TXTFileReaderThread has interface class Runnable.</br>
 * 
 * @author YuJin
 *
 */

public class TXTFileReaderThread implements Runnable{
	File file;
	ArrayList<String> message = new ArrayList<String>();

	/**
	 * 
	 * It is constructor.</br>
	 * 
	 * @param file
	 */
	public TXTFileReaderThread(File file) {
		this.file = file;
	}

	/**
	 * 
	 * This method is for running.</br>
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		getMessagesFromTXTFiles(file);
		message = parsingTXTFile(message);
	}

	/**
	 * 
	 * This method is for getting string message from txt files.</br>
	 * 
	 * @param file
	 * @return
	 */
	private ArrayList<String> getMessagesFromTXTFiles(File file) {
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

		return message;
	}

	/**
	 * 
	 * This method is for parsing txt file(making same shape).</br>
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

	/**
	 * 
	 * This method is for getting message variable.</br>
	 * 
	 * @return
	 */
	public ArrayList<String> getMessage() {
		return message;
	}

}
