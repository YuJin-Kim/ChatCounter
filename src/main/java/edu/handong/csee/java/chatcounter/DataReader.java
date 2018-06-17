package edu.handong.csee.java.chatcounter;

import java.io.File;
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
		ArrayList<String> csv = new ArrayList<String>();
		ArrayList<String> txt = new ArrayList<String>();
		ArrayList<CSVFileReaderThread> CSVworkers = new ArrayList<CSVFileReaderThread>();
		ArrayList<TXTFileReaderThread> TXTworkers = new ArrayList<TXTFileReaderThread>();
		ArrayList<Thread> csvThreads = new ArrayList<Thread>();
		ArrayList<Thread> txtThreads = new ArrayList<Thread>();
		
		for (File f : files) {
			if(f.getName().endsWith(".csv")) {
				CSVFileReaderThread csvFileReader = new CSVFileReaderThread(f);
				CSVworkers.add(csvFileReader);
				Thread CSVworker = new Thread(csvFileReader);
				csvThreads.add(CSVworker);
				
				CSVworker.start();
				//csv.addAll(csvFileReader.getMessage());
			}
			else {
				TXTFileReaderThread txtFileReader = new TXTFileReaderThread(f);
				TXTworkers.add(txtFileReader);
				Thread TXTworker = new Thread(txtFileReader);
				txtThreads.add(TXTworker);
				
				TXTworker.start();
				//txt.addAll(txtFileReader.getMessage());
			}
		}
		
		for(Thread thread : csvThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(Thread thread : txtThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (CSVFileReaderThread workers : CSVworkers)
			csv.addAll(workers.getMessage());
		
		for (TXTFileReaderThread workers : TXTworkers)
			txt.addAll(workers.getMessage());

		messages.addAll(csv);
		messages.addAll(txt);

		return messages;
	}

}
