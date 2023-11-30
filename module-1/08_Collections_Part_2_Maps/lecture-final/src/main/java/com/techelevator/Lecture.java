package com.techelevator;

import java.util.*;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

		// create a map
		// LinkedHashMap -> preserve insertion order
		// TreeMap -> remains sorted

		Map<String, Integer> namesAndZipCodes = new HashMap<>();

		Map<String, Integer> preInitialized = new HashMap<>(Map.of("Dan", 15227, "David", 15222));


		int howManyEntries = namesAndZipCodes.size();

		// add to the map
		/*
		    KEYS	VALUES
			Dan   -> 15227
			David -> 15226
		 */
		namesAndZipCodes.put("Dan", 15227);
		namesAndZipCodes.put("David", 15222);

		namesAndZipCodes.put("David", 15226); // replace "David"


		Integer davidsZipCode = namesAndZipCodes.get("David"); // retrieve value assoc. with "David". if not there then returns null

		if (namesAndZipCodes.containsKey("David")) {
			davidsZipCode = namesAndZipCodes.get("David");
		}

		namesAndZipCodes.remove("David"); // removes both key and value


		namesAndZipCodes.put("Bobby", 90210);
		namesAndZipCodes.put("Sally", namesAndZipCodes.get("Bobby"));

//		for (DataType variableName : collection)

		for (String name : namesAndZipCodes.keySet()) {

			Integer zipCode = namesAndZipCodes.get(name);

			System.out.println("Name: " + name + " Zip: " + zipCode);
		}

		for (Map.Entry<String, Integer> keyValuePairing : namesAndZipCodes.entrySet()) {
			String name = keyValuePairing.getKey();
			Integer zipCode = keyValuePairing.getValue();

			System.out.println("Name: " + name + " Zip: " + zipCode);
		}


		System.out.println("####################");
		System.out.println("        SETS");
		System.out.println("####################");
		System.out.println();

		Set<String> names = new HashSet<>();


		// ask for the size
		int howManyNames = names.size();

		// add to the Set
		names.add("Tom");
		names.add("Walt");
		names.add("Julie");
		names.add("Walt"); // won't do anything

		for (String name : names) {
			System.out.println(name);
		}

		// remove from the Set. returns true if it did
		names.remove("Walt");

		if (names.contains("Walt")) {
			System.out.println("Walt is in the Set");
		} else {
			System.out.println("Walt is not in the Set");
		}




//		List<String> namesList = new ArrayList<>();
//		namesList.add("Tom");
//		namesList.add("Walt");
//		namesList.add("Julie");
//
//		for (String name : namesList) {
//			System.out.println(name);
//		}
//		for (int i = 0; i < namesList.size(); i++) {
//			String name = namesList.get(i);
//			System.out.println(name);
//		}


		System.out.println(getDefinitionsForWord("JaVa"));

		System.out.println(getDefinitionsForWord(null));

	}

	/*
		Problems:

		1) Determine if a list has duplicates.

		2) Given a list, remove all duplicates from it.

		3) Build a structure that represents a dictionary of words and their definitions.

		4) Given a list of integers, find and return the integer that appears most frequently.
		In the case of a tie, return the largest value.

		5) Given two Strings, return true if they are anagrams.

	 */

	/*
		dictionary ->
		{
			"Java"  ->  [ "Coffee", "A programming language" ],

			"C#"	->	[ "A musical note", "A programming language" ]
		}


		javaDefinitions -> [ "Coffee", "A programming language" ]
		cSharpDefinitions -> [ "A musical note", "A programming language" ]


		coffee
	 */
	public static List<String> getDefinitionsForWord(String word) {
		if (word == null) {
			return List.of(); // may be better to return null
		}

		Map<String, List<String>> dictionary = new HashMap<>();

		List<String> javaDefinitions = new ArrayList<>();
		javaDefinitions.add("Coffee");
		javaDefinitions.add("A programming language");

		dictionary.put("java", javaDefinitions);

		List<String> cSharpDefinitions = new ArrayList<>();
		cSharpDefinitions.add("A musical note");
		cSharpDefinitions.add("A programming language");

		dictionary.put("c#", cSharpDefinitions);

		word = word.toLowerCase();

		if (dictionary.containsKey(word)) {
			return dictionary.get(word);
		} else {
			return List.of("I'm not sure."); // may be better returning null
		}
	}


	/*
		[ 50, 30, 22, 30, 50, 50 ] -> 50

		freqMap ->
		{
			50 -> 3
			30 -> 2
			22 -> 1
		}

		mostFreqNum -> 50
		frequency 	->  3

	 */
	public static int findMostFrequent(List<Integer> nums) {
		int mostFrequentNum = -1;
		int frequency = 0;

		Map<Integer, Integer> freqMap = new HashMap<>();

		for (Integer num : nums) {

			if (freqMap.containsKey(num)) {
				int updatedFrequency = freqMap.get(num) + 1;
				freqMap.put(num, updatedFrequency);
			} else {
				freqMap.put(num, 1);
			}

			int currentNumFrequency = freqMap.get(num);
			if (currentNumFrequency > frequency) {
				mostFrequentNum = num;
				frequency = currentNumFrequency;
			} else if (currentNumFrequency == frequency && num > mostFrequentNum) {
				mostFrequentNum = num;
			}
		}

		return mostFrequentNum;
	}

}
