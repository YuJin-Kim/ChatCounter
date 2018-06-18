package edu.handong.csee.java.chatcounter;

import java.io.File;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	 * This method is for reading files(csv and txt).</br>
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
		//		ArrayList<Thread> csvThreads = new ArrayList<Thread>();
		//		ArrayList<Thread> txtThreads = new ArrayList<Thread>();

		ExecutorService executor = Executors.newFixedThreadPool(Cli.thread);
		ArrayList<Callable<Object>> calls = new ArrayList<Callable<Object>>();

		for (File f : files) {
			if(f.getName().endsWith(".csv")) {
				Runnable csvWorker = new CSVFileReaderThread(f);
				CSVworkers.add((CSVFileReaderThread)csvWorker);
				calls.add(Executors.callable(csvWorker));
			}
			else {
				Runnable txtWorker = new TXTFileReaderThread(f);
				TXTworkers.add((TXTFileReaderThread)txtWorker);
				calls.add(Executors.callable(txtWorker));
			}
		}

		try {
			executor.invokeAll(calls); // This line will be terminated after all threads are terminated.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();

		for (CSVFileReaderThread workers : CSVworkers)
			csv.addAll(workers.getMessage());

		for (TXTFileReaderThread workers : TXTworkers)
			txt.addAll(workers.getMessage());

		messages.addAll(csv);
		messages.addAll(txt);

		return messages;
	}

}
