package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class MainChatCounter {

	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		MessageFilter filter = new MessageFilter();
		//DataReaderForCSV readerCSV = new DataReaderForCSV();
		//DataReaderForTXT readerTXT = new DataReaderForTXT();
		//DataWriter dataWriter = new DataWriter();
		
		ChatMessageCounter counter= new ChatMessageCounter();
		Map<String, Integer> user = new HashMap<String, Integer>();
		
		ArrayList<String> message;
		ArrayList<String> txt;
		ArrayList<String> csv;
		
		message = dataReader.getData(args[0]);
		
		txt = filter.filterForTXT(message);
		csv = filter.filterForCSV(message);
		
		counter.countTXT(user, txt);
		counter.countCSV(user, csv);
	}

}
