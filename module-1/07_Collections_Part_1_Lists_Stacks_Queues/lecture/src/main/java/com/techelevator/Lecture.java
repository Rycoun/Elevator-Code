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
		List<String> namesA = List.of("Walt", "Tom");


		// create a list from another list
		List<String> test = new ArrayList<>(namesA);



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




		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		names.add(1, "Jennifer");



		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");

		names.remove("Jennifer");
		names.remove(0);





		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");
	if (names.contains("bob"));




		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");
		int indexOfBob = names.indexOf("Bob");
		int indexOfWalt = names.indexOf("Walt");
		names.set(2, "Tom");
		names.set(1, "Walt");




		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		List<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(5);
		nums.add(7);

		int[] numsArr = new int[nums.size()];
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
		// dataList - -50, 132, 45

		Collections.sort(dataList);

		// dataList - -50, 45, 132




		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");

		Collections.reverse(dataList);




		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();
		int sum = 0;
		for (int num : dataList) {
			sum = sum + num;

		}




		for (String name : names) {
			System.out.print(name);
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
