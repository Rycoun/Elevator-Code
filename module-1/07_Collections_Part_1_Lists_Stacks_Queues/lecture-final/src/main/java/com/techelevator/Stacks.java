package com.techelevator;

import java.util.Stack;

public class Stacks {
    /*
		Example challenge questions:

		1)  Given two strings s and t, return true if they are equal when both are typed into empty text editors.
		    '#' means a backspace character.

			Input:            s = "ab#c", t = "ad#c"
			Output:         true
			Explanation: both result in "ac"

			Input:            s="ab##", t = "c#d#"
			Output:        true
			Explanation: both result in ""

			Input:            s = "a#c", t = "b"
			Output:        false
			Explanation: s="c" and t="b"

		2)	Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
			input string is valid. An input string is valid if:

			Open brackets must be closed by the same type of brackets.
			Open brackets must be closed in the correct order.
			Every close bracket has a corresponding open bracket of the same type.
		 */

    public static void main(String[] args) {

        // Create a Stack
        Stack<String> urls = new Stack<>();

        urls.push("https://techelevator.com");
        urls.push("https://google.com");
        urls.push("https://espn.com");

        /*

              google
              techelevator   espn
         */

        String currentUrl = urls.pop(); // remove and return what's at the top of the stack

        currentUrl = urls.peek(); // show me what's at the top of the stack

        while (urls.size() > 0) { // alternative: !urls.isEmpty()
            currentUrl = urls.pop();

            System.out.println(currentUrl);
        }

        int y = 2;
        doStuff();

    }


    public static void doStuff() {
        int x = 1;

        int[] nums = { 1, 2, 5 };

        System.out.println(nums[100]);

        System.out.println("doing stuff");
    }
}
