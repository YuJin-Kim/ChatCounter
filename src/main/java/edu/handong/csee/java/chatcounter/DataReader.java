package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class DataReader {
	
	public ArrayList<String> getData(String strDir) {
		File myDir = getDirectory(strDir);
		File[] files = getlistOfFilesFromDirectory(myDir);
		ArrayList<String> messages = readFiles(files);
		
		return messages;
	}
	
	public File getDirectory(String strDir) {
		try {
			 File myDirectory = new File(strDir);
			 return myDirectory;
		}catch(Exception e) {
			System.out.println("It is invalid path.");
			return null;
		}
	}

	public File[] getlistOfFilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}
	
	public ArrayList<String> readFiles(File[] files) {
		ArrayList<String> messages = new ArrayList<String>();
		
		try {
			for(File f : files) {
				FileReader fileReader = new FileReader(f);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				String line = "";
				while((line = bufferedReader.readLine()) != null)
					messages.add(line);
				
				bufferedReader.close();
			}
		}catch (FileNotFoundException e) {
		}catch (IOException e) {
			System.out.println(e);
		}
		
		return messages;
	}
	
}
