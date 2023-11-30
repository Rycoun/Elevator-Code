package com.techelevator.maps;

import java.util.Map;
import java.util.HashMap;

public class MapsExercise {

    /*
     * Given an array of Strings, return a Map<String, Integer> with a key for each different String, with the value the
     * number of times that String appears in the array.
     *
     * ** A CLASSIC **
     *
     * wordCount(["ba", "ba", "black", "sheep"]) → {"ba" : 2, "black": 1, "sheep": 1 }
     *
     */
    public Map<String, Integer> wordCount(String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            if (wordCount.containsKey(word)) {
                Integer currentCount = wordCount.get(word);
                wordCount.put(word, currentCount + 1);
            } else {
                wordCount.put(word, 1);
            }
        }

        return wordCount;
    }
}
