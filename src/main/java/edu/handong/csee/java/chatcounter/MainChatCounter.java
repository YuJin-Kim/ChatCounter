package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.lang.NullPointerException;

public class MainChatCounter {

	public static void main(String[] args) {
		DataReader dataReader = new DataReader();
		DataReaderForCSV readerCSV = new DataReaderForCSV();
		DataReaderForTXT readerTXT = new DataReaderForTXT();
		MessageFilter filter = new MessageFilter();
		ChatMessageCounter counter= new ChatMessageCounter();
		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
		DataWriter dataWriter = new DataWriter();
		Cli cli = new Cli();

		ArrayList<String> message;
		ArrayList<String> csv;
		ArrayList<String> txt;
		String path, file;

		cli.run(args);
		path = cli.path;
		file = cli.file;
		try {
			message = dataReader.getData(path);
			
			csv = readerCSV.run(message);
			txt = readerTXT.run(message);

			message.clear();
			message.addAll(csv);
			message.addAll(txt);

			filter.filterOverlap(message);

			map = counter.run(message);

			dataWriter.printOutputHashMap(map, file);
		}catch(NullPointerException e) {
			System.out.println("Path is invalid");
		}
	}

}