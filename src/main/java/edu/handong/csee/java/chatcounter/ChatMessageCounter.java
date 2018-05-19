package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class ChatMessageCounter {
	
	public void countTXT(Map<String, Integer> user, ArrayList<String> message) {
		DataReaderForTXT dataReader = new DataReaderForTXT();
		
	}
	
	public void countCSV(Map<String, Integer> user, ArrayList<String> message) {
		DataReaderForCSV dataReader = new DataReaderForCSV();
		
	}
}
