package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataReader {
	
	public ArrayList<String> getData(String strDir) {
		File myDir = getDirectory(strDir);
		File[] files = getlistOfFilesFromDirectory(myDir);
		for (int i=0; i<20; i++)
			System.out.println(files[i].getName());
		ArrayList<String> messages = readFiles(files);
		
		return messages;
	}
	
	public File getDirectory(String strDir) {
		File myDirectory = new File(strDir);
		
		return myDirectory;
	}

	public File[] getlistOfFilesFromDirectory(File dataDir) {
		
		return dataDir.listFiles();
	}
	
	public ArrayList<String> readFiles(File[] files) {
		ArrayList<String> messages = new ArrayList<String>();
		
		try {
			for(File f : files) {
				Scanner scan = new Scanner(f);
				while(scan.hasNextLine())
					messages.add(scan.nextLine());
			}
			Scanner scan2 = new Scanner(files[11]);
			while(scan2.hasNextLine())
				System.out.println(scan2.nextLine());
		}
		catch (FileNotFoundException e) {
			
		}
		
		return messages;
	}
	
}
