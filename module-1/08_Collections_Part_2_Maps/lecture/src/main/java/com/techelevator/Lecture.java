package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();
		Map<String, Integer> namesAndZips = new TreeMap<>();

		int howManyEnters = namesAndZips.size();

		namesAndZips.put("Dan", 15227);
		namesAndZips.put("David", 15222);
		namesAndZips.put("David", 15226); // repleace "david"

		Integer davidZip = namesAndZips.get("David"); // gets values with "David". if not there return null

		if (namesAndZips.containsKey("David")) {
			davidZip = namesAndZips.get("David");
		}

		namesAndZips.remove("David"); // removes key and value.


		for (String name : namesAndZips.keySet()) ;
		// Integer zip = namesAndZips.get();

		for (Map.Entry<String, Integer> keyVal : namesAndZips.entrySet()) {
			String name = keyVal.getKey();
			Integer zipCode = keyVal.getValue();

		}

		System.out.println("####################");
		System.out.println("        SETS");
		System.out.println("####################");
		System.out.println();
		Set<String> names = new HashSet<>();

		// ask for size
		int howManyNames = names.size();
		names.add("Tom");
		names.add("Walt");
		names.add("Julie");

		for (String name : names) {
			System.out.print(name);

		}
		// remove from set, returns true if it removed.
		names.remove("Walt");

		if (names.contains("Walt")) {
			System.out.println("Walt is in the Set");
		} else {
			System.out.println("Walt not in the Set");
		}


	}

	/*
		Problems:

		1) Determine if a list has duplicates.

		2) Given a list, remove all duplicates from it.

		3) Build a structure that represents a dictionary of words and their definitions.




		4) Given a list of integers, find and return a number that appears most frequently.
		In the case of a tie, return the largest value.

		5) Given two Strings, return true if they are anagrams.




	 */
	public static List<String> getDefForWord(String word) {
		Map<String, List<String>> dict = new HashMap<>();
		List<String> javaDef = new ArrayList<>();
		javaDef.add("Coffee");
		javaDef.add("A programming Lang");

		dict.put("Java", javaDef);

		List<String> cDef = new ArrayList<>();

		cDef.add("Musical note");
		cDef.add("Programming");

		dict.put("C#", cDef);
		if (word == null) {
			return null;
		}
		if (dict.containsKey(word)) {
			return dict.get(word);
		} else {
			return List.of("I'm not sure.");

		}
	}


	public static int findMost(List<Integer> nums) {
		int mostFreq = -1;
		int frequency = 0;


		Map<Integer, Integer> freqMap = new HashMap<>();
		for (Integer num : nums) {
			if (freqMap.containsKey(num)) {
				int updateFreq = freqMap.get(num);

				freqMap.put(num, updateFreq);
			} else {
				freqMap.put(num, 1);

			}
			int currVal = freqMap.get(num);
			if (currVal > frequency) ;
			mostFreq = num;
			frequency = currVal;
		}
	}}
	}
