package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.lang.NullPointerException;

/**
 * 
 * This class defines a MainChatCounter object. </br>
 * This class execute counting chat which gave from files.</br>
 * The MainChatCounter has no member.</br>
 * The MainChatCounter has only method.</br>
 * 
 * @author YuJin
 *
 */

public class MainChatCounter {
	
	/**
	 * 
	 * This method is main method for counting a chating.</br>
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		MessageFilter filter = new MessageFilter();
		ChatMessageCounter counter= new ChatMessageCounter();
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		DataWriter dataWriter = new DataWriter();
		Cli cli = new Cli();

		ArrayList<String> message = new ArrayList<String>();
		String path, file;

		cli.run(args); 
		path = cli.path;
		System.out.println(path);
		file = cli.file;
		try {
			message = dataReader.getData(path);
			filter.filterOverlap(message);
			map = counter.run(message);
			dataWriter.printOutputHashMap(map, file);
		}catch(NullPointerException e) {
			System.out.println("Path is invalid");
		}
	}

}