package edu.handong.csee.java.chatcounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageFilter {
	
	public void filterOverlap(ArrayList<String> message) {
		int i, j;
		
		for (i = 0; i < message.size(); i++) {
			String pattern = "([0-9]+-[0-9]+-[0-9]+\\s[0-9]+:[0-9]+):[0-9]+\\,(\\\".+\\\"\\,\\\".+\\\")";
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(message.get(i));
			if (m.find())
				for (j = i+1; j < message.size(); j++ ) {
					Matcher m2 = p.matcher(message.get(j));
					if (m2.find() && m.group(1).equals(m2.group(1)) && m.group(2).equals(m2.group(2))) {
						message.remove(j);
						j--;
					}
				}
		}
		
	}
	
}