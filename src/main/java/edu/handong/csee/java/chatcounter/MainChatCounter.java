package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.Scanner;

public class MainChatCounter {

	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		DataWriter dataWriter = new DataWriter();
		
		ArrayList<String> message;
		
		message = dataReader.getData(args[0]);
		//dataWriter.printOutput(message);
		
	}

}
