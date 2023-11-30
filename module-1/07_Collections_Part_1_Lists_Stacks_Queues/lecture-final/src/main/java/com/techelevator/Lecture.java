package com.techelevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

		// create a list
		List<String> names = new ArrayList<>();
		names.add("Walt");
		names.add("Tom");

		// create a list with initialized values.
		String[] namesArray = { "Walt", "Tom" };
		List<String> namesPreInitialized = List.of("Walt", "Tom");

		// create a list from another list
		List<String> namesFromAnother = new ArrayList<>(namesPreInitialized);
		List<String> namesFromAnother2 = new ArrayList<>(List.of("Walt", "Tom"));

		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");

		names.add("Walt");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		System.out.println(names);

		String[] namesArray2 = { "Walt", "Tom", "Walt" };
		System.out.println(Arrays.toString(namesArray2));

		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		names.add(1, "Jennifer"); // "Walt", "Jennifer", "Tom", "Walt"
		names.add(names.size() / 2, "Jennifer"); // "Walt", "Jennifer", "Jennifer", "Tom", "Walt"


		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");

		names.remove("Jennifer"); // "Walt", "Jennifer", "Tom", "Walt"
		names.remove(0); // "Jennifer", "Tom", "Walt"

		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");

		if (names.contains("Bob")) {
			System.out.println("Bob is in the list");
		}

		boolean doesContain = names.contains("Walt");
		if (doesContain) {
			System.out.println("Walt is in the list");
		}

		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equalsIgnoreCase("Walt")) {
				doesContain = true;
			}
		}

		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		int indexOfBob = names.indexOf("Bob"); // -1 (Bob is not there)
		int indexOfWalt = names.indexOf("Walt"); // 0

		// "Jennifer" "Tom" "Walt"
		// "Jennifer" "Walt" "Tom"
		indexOfWalt = names.indexOf("Walt");
		int indexOfTom = names.indexOf("Tom");

		if (indexOfWalt > -1) {
			names.set(indexOfWalt, "Tom");
		}

		if (indexOfTom > -1) {
			names.set(indexOfTom, "Walt");
		}


		names.set(2, "Bob");
		namesArray[2] = "Bob";


		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		List<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(5);
		nums.add(7);

		int[] numsArr = new int[nums.size()]; // [0,0,0]
		for (int i = 0; i < nums.size(); i++) {
			numsArr[i] = nums.get(i);
		}

		Integer[] numsArr2 = new Integer[nums.size()];
		nums.toArray(numsArr2);


		Integer[] data = { -50, 132, 45 };

		List<Integer> dataList = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			dataList.add(data[i]);
		}

		List<Integer> dataList2 = Arrays.asList(data);

		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");

		// dataList -> -50, 132, 45

		Collections.sort(dataList); // ascending is default
		// dataList -> -50, 45, 132

		Collections.sort(dataList, Collections.reverseOrder()); // descending
		// dataList -> 132, 45, -50



		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");

		Collections.reverse(dataList);
		// dataList -> -50, 45, 132


		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();

		// "Jennifer" "Walt" "Tom"
		//                     ^

		for (String name : names) {
			System.out.println(name);
		}

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		int sum = 0;
		for (int currNum : dataList) {
			sum = sum + currNum;
		}


		List<Integer> randomStuff = new ArrayList<>();
		randomStuff.add(1);
		randomStuff.add(2);
		randomStuff.add(10);
		randomStuff.add(null);

		int elemAtIndex3 = 0;

		if (randomStuff.get(3) != null) {
			elemAtIndex3 = randomStuff.get(3);
		}


		System.out.println("####################");
		System.out.println("       STACKS and QUEUES -> see other files");
		System.out.println("####################");
		System.out.println();


		/*

		Challenge Problems:

		1) Find the min and max and return the list without them

		2) Determine if there are duplicates in a given list

		3) Given two integer lists, determine if there is a way to divide the lists into two lists with equal sums.
		   Return true or false.

		 */

	}
}
