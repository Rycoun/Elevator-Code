package com.techelevator.bank;

import java.util.Map;

public class MapExercises {

    /*

        if peter has at least $50, transfer half of peter's money to paul
        balances are represented as pennies i.e. $50 is 5,000 pennies
     */
    public Map<String, Integer> robPeterToPayPaul(Map<String, Integer> startingBalances) {

        if (startingBalances.get("Peter") < 5_000) {
            return startingBalances;
        }

        int halfOfPetersMoney = startingBalances.get("Peter") / 2;

        startingBalances.put("Paul", startingBalances.get("Paul") + halfOfPetersMoney);
        startingBalances.put("Peter", startingBalances.get("Peter") - halfOfPetersMoney);

        return startingBalances;
    }

}
