package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

/**
 * 
 * This class defines DataReader object.</br>
 * The DataReader class has no member.</br>
 * The DataReader class has getData(), getDirectory() and getlistOfFiles() methods.</br>
 * 
 * @author YuJin
 *
 */

public class DataReader {
	
	/**
	 * 
	 * This method is for getting data(run).</br>
	 * 
	 * @param strDir
	 * @return
	 */
	public ArrayList<String> getData(String strDir) {
		File myDir = getDirectory(strDir);
		File[] files = getlistOfFilesFromDirectory(myDir);
		ArrayList<String> messages = readFiles(files);
		
		return messages;
	}
	
	/**
	 * 
	 * This method is for getting directory path.</br>
	 * 
	 * @param strDir
	 * @return
	 */
	
	public File getDirectory(String strDir) {
		try {
			 File myDirectory = new File(strDir);
			 return myDirectory;
		}catch(Exception e) {
			System.out.println("It is invalid path.");
			return null;
		}
	}
	
	/**
	 * 
	 * This method is for getting file list from directory path.</br>
	 * 
	 * @param dataDir
	 * @return
	 */

	public File[] getlistOfFilesFromDirectory(File dataDir) {
		return dataDir.listFiles();
	}
	
	/**
	 * 
	 * This method is for reading files.</br>
	 * 
	 * @param files
	 * @return
	 */
	
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
