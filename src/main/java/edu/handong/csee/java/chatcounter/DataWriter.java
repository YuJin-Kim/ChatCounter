package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * 
 * This class defines DataWriter object.</br>
 * The DataWriter class has no member.</br>
 * The DataWriter class has only printOutputHashMap() method.</br>
 * 
 * @author YuJin
 *
 */

public class DataWriter {

	/**
	 * 
	 * This method is printing output of hashmap.</br>
	 * 
	 * @param map
	 * @param f
	 */

	public void printOutputHashMap(HashMap<String, Integer> map, String f) {
		Set<String> set = map.keySet();

		try {
			File file = new File(f);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

			if(file.isFile() && file.canWrite()) {
				bufferedWriter.write("kakao_id, count\n");
				for (String key : set)
					bufferedWriter.write(key + ", " + map.get(key)+"\n");
				bufferedWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
