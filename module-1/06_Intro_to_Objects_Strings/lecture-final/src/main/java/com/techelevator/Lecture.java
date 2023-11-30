package com.techelevator;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("************************************");
		System.out.println("****** MAKING A STRING OBJECT ******");
		System.out.println("************************************");

		/* The String class gets special treatment in the Java language.  One
		 * example of this is that there is a literal representation of a
		 * String (i.e. characters appearing between two double quotes.  This
		 * is not the case for most classes) */

		/* create a new instance of String using a literal */

		String name = "Walt";

		String name2 = new String("Walt");

		String name3 = "Walt";

		if (name == name3) {
			System.out.println("name and name3 are equal");
		}

		if (name == name2) {
			System.out.println("name and name2 are equal");
		}

		if (name.equals(name2)) {
			System.out.println("name and name2 are .equals true");
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
		 *
		 * substring
		 *
		 * toLowerCase
		 * toUpperCase
		 *
		 * replace
		 * trim
		 * split
		 */

		String helloWorld = "Hello World!";

		char w = helloWorld.charAt(6);

		System.out.println(helloWorld.charAt(6));

		if (helloWorld.startsWith("Hello")) {

		}

		if (helloWorld.endsWith("World!")) {

		}

		if (helloWorld.contains(" W ")) {

		}

		String[] names = new String[5];

		for (int i = 0; i < names.length; i++) {
			if (names[i].contains("Jason")) {

			}
		}

		int indexOfHello = helloWorld.indexOf("Hello"); // returns 0
		int indexOfWorld = helloWorld.indexOf("World"); // returns 6
		int indexOfBob = helloWorld.indexOf("Bob"); // returns -1

		int indexOfL = helloWorld.indexOf("l"); // returns 2
		int lastIndexOfL = helloWorld.lastIndexOf("l"); // returns 9

		int howManyLs = 0;
		for (int i = 0; i < helloWorld.length(); i++) {
			if (helloWorld.charAt(i) == 'l') {
				howManyLs++;
			}
		}

		// "Hello World!"

		String substring4 = helloWorld.substring(4); // "o World!"

		String substring4to7 = helloWorld.substring(4, 7); // "o W"




		String myNewString = "My New String";

		String uppercaseMyNewString = myNewString.toUpperCase(); // "MY NEW STRING"

		String lowercaseMyNewString = myNewString.toLowerCase(); // "my new string"

		if (myNewString.toLowerCase().contains("new")) {

		}

		if (myNewString.equalsIgnoreCase("my new string")) {

		}

		// Hello World! -> HelloWorld!
		String replacement = helloWorld.replace(" ", "");


		String blah = " sdafasdf asdf  edddadsf    ";

		String blahTrimmed = blah.trim(); // "sdafasdf asdf  edddadsf"

		String noSpaces = blah.replace(" ", ""); // remove all whitespaces "sdafasdfasdfedddadsf"


		String csv = "1,2,3,4,5,6";

		String[] csvColumnData = csv.split(","); // [ "1", "2", "3", "4", "5", "6" ]



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

		StringBuilder indices = new StringBuilder();

		for (int i = 0; i <= 50; i++) {
			indices.append(" ").append(i);

			//indices.insert(0, i); // put value of i at the beginning
		}

		String result = indices.toString().trim();

		// " 0 1 2 3 4 5 6 7 8 ... 50"
	}


	public static boolean isPalindrome(String testString) {
		// is a string a palindrome?
		// Palindrome: a string that reads the same left to right and right to left
		// racecar
		//

		// i = 6


		// tacocat
		// return true if a string is a palindrome

		for (int i = 0; i < testString.length() / 2; i++) {
			if (testString.charAt(i) != testString.charAt(testString.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	/*

		Return true if the first two characters appear again later on in the String.

		blah blah blah! -> true

		firstTwoChars = "bl"

		'b' == 'a' && 'l' == 'h'

		Hello World! -> false

		firstTwoChars = "He"

	 */
	public static boolean firstTwoCharsExistElsewhere(String testString) {
		String firstTwoChars = testString.substring(0, 2);

		for (int i = 2; i < testString.length() - 1; i++) {
			String nextTwoChars = testString.substring(i, i+2);

			if (firstTwoChars.equals(nextTwoChars)) {
				return true;
			}
		}

		return false;
	}

	// bbb
	public static boolean firstTwoCharsExistElsewhereAgain(String testString) {
		String firstTwoChars = testString.substring(0, 2);

		return testString.lastIndexOf(firstTwoChars) > 1;

//		return testString.substring(2).contains(firstTwoChars);
	}
}
