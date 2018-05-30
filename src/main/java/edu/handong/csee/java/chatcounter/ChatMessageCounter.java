package edu.handong.csee.java.chatcounter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * This class defines ChatMessageCounter object.</br>
 * The ChatMessageCounter class has no member.</br>
 * The ChatMessageCounter class has run(), count() and sorting() methods.
 * 
 * @author YuJin
 * 
 */

public class ChatMessageCounter {
	
	/**
	 * 
	 * This method is running(counting chat message and sorting to hashmap).</br>
	 * 
	 * @param message
	 * @return
	 */
	
	public LinkedHashMap<String, Integer> run(ArrayList<String> message) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		
		map = count(message);
		sortedMap = sorting(map);
		
		return sortedMap;
	}
	
	/**
	 * 
	 * This method is counting chat message.</br>
	 * 
	 * @param message
	 * @return
	 */
	
	private HashMap<String, Integer> count(ArrayList<String> message) {
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		boolean exist = false;
		String name="";
		
		for (String line : message) {
			String pattern = "[0-9]+-[0-9]+-[0-9]+\\s[0-9]+:[0-9]+:[0-9]+\\,\\\"(.+)\\\"\\,\\\".+\\\"";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(line);
			
			if (m.find())
				name = m.group(1);
			
			Set<String> set = map.keySet();
			
			for (String key : set)
				if (name.equals(key)) {
					map.put(key, (map.get(key)+1));
					exist = true;
				}
			
			if (exist == false)
				map.put(name, 1);
			
			exist = false;
		}
		
		return map;
	}
	
	/**
	 * 
	 * This method is for sorting the list.</br>
	 * 
	 * @param map
	 * @return
	 */
	
	private LinkedHashMap<String, Integer> sorting(final HashMap<String, Integer> map) {
		LinkedHashMap<String, Integer> sortedList = new LinkedHashMap<String, Integer>();
		ArrayList<String> list = new ArrayList<String>();
		list.addAll(map.keySet());
		
		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			public int compare(Object o1,Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable<Object>)v1).compareTo(v2);
			}
		});
		
		Collections.reverse(list);
		
		for (String key : list)
			sortedList.put(key, map.get(key));
		
		return sortedList;
		
	}
	
}
