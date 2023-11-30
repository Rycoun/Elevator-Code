package com.techelevator;
import java.util.*;

public class Stacks {
    public static void main(String[] args) {

        Stack<String> urls = new Stack<>();
        urls.push("www.youtube.com");
        urls.push("google.com");
        urls.push("git.com");

        String currentUrl = urls.pop(); // remove and return what on top
        currentUrl = urls.peek(); // show me
            while (urls.size() > 0);
            currentUrl = urls.pop();
        System.out.println(currentUrl);

    }

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
}
