package com.techelevator;

import java.util.HashSet;
import java.util.Set;

public class AlgorithmicComplexity {

    /*
        Add the first and last elements of an array.

        This is an example of a constant time algorithm, O(1).

        No matter how large the array is, the number of operations is (roughly) the same.

        Examples:
        addFirstAndLast(null) -> 0
        addFirstAndLast([]) -> 0
        addFirstAndLast([5]) -> 10
        addFirstAndLast([1, 5, 2, 4, 3]) -> 4
     */
    public long addFirstAndLast(int[] numbers) {
        if(numbers == null || numbers.length == 0) {
            return 0;
        }

        return (long) numbers[0] + numbers[numbers.length - 1];
    }

    /*
        Search for an element in a sorted array.

        This is an example of a logarithmic time algorithm, O(log(n)).

        Examples:
        binarySearch(null, 5) -> false
        binarySearch([], 3) -> false
        binarySearch([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], 2) -> true
        binarySearch([10, 20, 30, 32, 35, 46, 57, 58, 59], 33) -> false

     */
    public boolean binarySearch(int[] numbers, int numberToFind) {
        if(numbers == null || numbers.length == 0) {
            return false;
        }

        int lowerBound = 0;
        int upperBound = numbers.length - 1;

        while (lowerBound <= upperBound) {
            int midpoint = lowerBound + (upperBound - lowerBound) / 2;

            if (numbers[midpoint] == numberToFind) {
                return true;
            } else if (numbers[midpoint] < numberToFind) {
                lowerBound = midpoint + 1;
            } else {
                upperBound = midpoint - 1;
            }
        }

        return false;
    }

    /*
        Search for an element value in an unsorted array.

        This is an example of a linear time algorithm, O(n).

        Examples:

        linearSearch(null, 'a') -> false
        linearSearch("", 'a') -> false
        linearSearch("The quick brown fox.", 'a') -> false
        linearSearch("The quick brown fox jumped over the lazy dog.", 'a') -> true
     */
    public boolean linearSearch(String sentence, char charToFind) {
        if(sentence == null || sentence.length() == 0) {
            return false;
        }

        for (int i = 0; i < sentence.length(); i++) {
            if(sentence.charAt(i) == charToFind) {
                return true;
            }
        }

        return false;
    }

    /*
        Calculate the sum of two integer arrays.

        This is an example of O(n + m).

        Examples:
        sumOfTwoArrays(null, null) -> 0
        sumOfTwoArrays([], []) -> 0
        sumOfTwoArrays([], [4, 5, 6]) -> 15
        sumOfTwoArrays([1, 2, 3], []) -> 6
        sumOfTwoArrays([1, 2, 3], [4, 5, 6]) -> 21
     */
    public long sumOfTwoArrays(int[] array1, int[] array2) {
        long sum = 0;

        if(array1 != null) {
            for(int num : array1) {
                sum += num;
            }
        }

        if(array2 != null) {
            for(int num : array2) {
                sum += num;
            }
        }

        return sum;
    }

    /*
        Check if an unsorted array contains duplicates. (Unoptimized algorithm).

        This is an example of a quadratic runtime, O(n^2)

        Examples:
        containsDuplicate(null) -> false
        containsDuplicate([]) -> false
        containsDuplicate([1, 3, -4, -2, 6, 5]) -> false
        containsDuplicate([1, 3, -4, -2, 3, 1]) -> true
     */
    public boolean containsDuplicate(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsDuplicateAgain(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }

        Set<Integer> numberSet = new HashSet<>();

        for (int num : numbers) {
            if (!numberSet.add(num)) {
                return true;
            }
        }

        return false;
    }

    /*
        Check common occurrences between two arrays. (Unoptimized solution)

        This has a runtime of O(n*m)

        Examples:
        commonOccurrences(null, null) -> 0
        commonOccurrences([], []) -> 0
        commonOccurrences([1, 2, 3], []) -> 0
        commonOccurrences([1, 2, 3], [2, 1, 5, 2]) -> 3
     */
    public long commonOccurrences(int[] a, int[] b) {
        if (a == null || a.length == 0 || b == null || b.length == 0) {
            return 0;
        }

        long count = 0;

        for(int num1 : a) {
            for (int num2 : b) {
                if (num1 == num2) {
                    count++;
                }
            }
        }

        return count;
    }

    // Generating combinations is x^n based runtime where x is how many items to combine

    // Generating permutations is n! runtime


    public static void main(String[] args) {
        AlgorithmicComplexity app = new AlgorithmicComplexity();

        /*
            Add First And Last
         */
        long addFirstLast = app.addFirstAndLast(null);
        System.out.println("app.addFirstAndLast(null) -> " + addFirstLast);

        addFirstLast = app.addFirstAndLast(new int[] { });
        System.out.println("app.addFirstAndLast(new int[] { }) -> " + addFirstLast);

        addFirstLast = app.addFirstAndLast(new int[] { 5 });
        System.out.println("app.addFirstAndLast(new int[] { }) -> " + addFirstLast);

        addFirstLast = app.addFirstAndLast(new int[] { 1_000_000_000, 5, 2, 4, 2_000_000_000 });
        System.out.println("app.addFirstAndLast(new int[] { }) -> " + addFirstLast);

        System.out.println();

        /*
            Binary Search
         */
        boolean binarySearchResult = app.binarySearch(null, 5);
        System.out.println("app.binarySearch(null, 5) -> " + binarySearchResult);

        binarySearchResult = app.binarySearch(new int[] { }, 3);
        System.out.println("app.binarySearch(new int[] { }, 3) -> " + binarySearchResult);

        binarySearchResult = app.binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 2);
        System.out.println("app.binarySearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 2) -> " + binarySearchResult);

        binarySearchResult = app.binarySearch(new int[] { 10, 20, 30, 32, 35, 46, 57, 58, 59 }, 33);
        System.out.println("app.binarySearch(new int[] { 10, 20, 30, 32, 35, 46, 57, 58, 59 }, 33) -> " + binarySearchResult);

        System.out.println();

        /*
            Linear Search
         */
        boolean linearSearchResult = app.linearSearch(null, 'a');
        System.out.println("app.linearSearch(null, 5) -> " + linearSearchResult);

        linearSearchResult = app.linearSearch("", 'a');
        System.out.println("app.linearSearch(new int[] { }, 3) -> " + linearSearchResult);

        linearSearchResult = app.linearSearch("The quick brown fox.", 'a');
        System.out.println("app.linearSearch(\"The quick brown fox.\", 'a') -> " + linearSearchResult);

        linearSearchResult = app.linearSearch("The quick brown fox jumped over the lazy dog.", 'a');
        System.out.println("app.linearSearch(\"The quick brown fox jumped over the lazy dog.\", 'a') -> " + linearSearchResult);

        System.out.println();

        /*
            Sum of two integer arrays
         */
        long sum = app.sumOfTwoArrays(null, null);
        System.out.println("app.sumOfTwoArrays(null, null) -> " + sum);

        sum = app.sumOfTwoArrays(new int[0], new int[0]);
        System.out.println("app.sumOfTwoArrays(new int[0], new int[0]) -> " + sum);

        sum = app.sumOfTwoArrays(new int[0], new int[] { 4, 5, 6 });
        System.out.println("app.sumOfTwoArrays(new int[0], new int[] { 4, 5, 6 }) -> " + sum);

        sum = app.sumOfTwoArrays(new int[] { 1, 2, 3}, new int[0]);
        System.out.println("app.sumOfTwoArrays(new int[] { 1, 2, 3}, new int[0]) -> " + sum);

        sum = app.sumOfTwoArrays(new int[] { 1, 2, 3}, new int[] { 4, 5, 6 });
        System.out.println("app.sumOfTwoArrays(new int[] { 1, 2, 3}, new int[] { 4, 5, 6 }) -> " + sum);

        System.out.println();

        /*
            Contains duplicate
         */

        boolean containsDuplicate = app.containsDuplicate(null);
        System.out.println("app.containsDuplicate(null) -> " + containsDuplicate);

        containsDuplicate = app.containsDuplicate(new int[0]);
        System.out.println("app.containsDuplicate(new int[0]) -> " + containsDuplicate);

        containsDuplicate = app.containsDuplicate(new int[] { 1, 3, -4, -2, 6, 5 });
        System.out.println("app.containsDuplicate(new int[] { 1, 3, -4, -2, 6, 5 }) -> " + containsDuplicate);

        containsDuplicate = app.containsDuplicate(new int[] { 1, 3, -4, -2, 3, 1 });
        System.out.println("app.containsDuplicate(new int[] { 1, 3, -4, -2, 3, 1 }) -> " + containsDuplicate);

        System.out.println();

        /*
            Common occurrences
         */

        long countCommonOccurrences = app.commonOccurrences(null, null);
        System.out.println("app.commonOccurrences(null) -> " + countCommonOccurrences);

        countCommonOccurrences = app.commonOccurrences(new int[0], new int[0]);
        System.out.println("app.commonOccurrences(new int[0], new int[0]) -> " + countCommonOccurrences);

        countCommonOccurrences = app.commonOccurrences(new int[] { 1, 2, 3 }, new int[0]);
        System.out.println("app.commonOccurrences(new int[] { 1, 2, 3 }, new int[0]) -> " + countCommonOccurrences);

        countCommonOccurrences = app.commonOccurrences(new int[] { 1, 2, 3 }, new int[] { 2, 1, 5, 2 });
        System.out.println("app.commonOccurrences(new int[] { 1, 2, 3 }, new int[] { 2, 1, 5, 2 }) -> " + countCommonOccurrences);

        System.out.println();

    }

}