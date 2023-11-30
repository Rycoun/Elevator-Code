package com.techelevator;


import java.util.Locale;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("************************************");
		System.out.println("****** MAKING A STRING OBJECT ******");
		System.out.println("************************************");

		/* The String class gets special treatment in the Java language.  One
		 * example of this is that there is a literal representation of a
		 * String (i.e. characters appearing between two double quotes.  This
		 * is not the case for most classes */

		/* create a new instance of String using a literal */
		String name = "Ryan";
		String name2 = new String("Ryan");
		String name3 = "Ryan";

		if (name.equals(name3)) {
			System.out.print("Name and name 3 are equal");
		}
		if (name.equals(name2)) {
			System.out.print("name and name 2 are equal");
		}
		if (name.equals((name2)) {
			System.out.print("name and name 2 are equal");
		}
		
		System.out.println();
		System.out.println("******************************");
		System.out.println("****** MEMBER METHODS ******");
		System.out.println("******************************");
		System.out.println();


		/* Other commonly used methods:
		 *
		 * charAt
		 *
		 * endsWith
		 * startsWith
		 * contains
		 *
		 * indexOf
		 * lastIndexOf
		 *
		 * length
		 * substring
		 *
		 * toLowerCase
		 * toUpperCase
		 * replace
		 * trim
		 * split
		 *
		 *
		 */

		String helloWorld = "Hello World! ";
		char l = helloWorld.charAt(3);




		int howManyl = 0;
		for (int i = 0; i < helloWorld; i++) {

		}


		String subString4 = helloWorld.substring(4); // "o world!"



		String myNewString = "my new String";

		String upper = myNewString.toUpperCase(); // "MY NEW STRING"

		String lower = myNewString.toLowerCase() // "my new string"

		if (myNewString.toLowerCase().contains("new")) {

		}

	String replace = helloWorld.replace("l", "L");


		String blah = " ofnqopfn qfinq q fnq ";
		String blahTrim = blah.trim();


		String cvs



		// is a string a palindrome?
		// palindrome: string that reads lef to right ( like a race car)
		// return true if string is a palindrome
		boolean isPalindrome = false;
		String newString = "Race";

		for (int i = 0; i < newString.length() / 2; i++) {
			if (newString.charAt(i) != newString.charAt(newString.length() -1 -i)) {
				isPalindrome = false;
				return;
			}
		}
		isPalindrome = true;






		System.out.println();
		System.out.println("**********************");
		System.out.println("****** EQUALITY ******");
		System.out.println("**********************");
		System.out.println();


		System.out.println();
		System.out.println("****************************************************");
		System.out.println("****** COMBINING MANY STRINGS (StringBuilder) ******");
		System.out.println("****************************************************");
		System.out.println();

		StringBuilder ind = new StringBuilder("");

		for (int i = 0; i <= 50; i++) {
			ind.append(i);
			ind.insert(0, i); // value of I at the beginning

		}
		String result = ind.toString().trim();


	}
	public static boolean firstTwoChart(String testString) {
		String first2 = testString.substring(0 , 2);
		for (int i = 2 i < testString.length(); i++) {
			if (first2.equals())


		}
	}



}
