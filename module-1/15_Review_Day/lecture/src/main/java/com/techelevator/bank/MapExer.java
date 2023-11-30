package com.techelevator.bank;

import java.util.Map;

public class MapExer {




    public Map<String, Integer> robPeterToPayPaul(Map<String, Integer> startingBalance) {

        if (startingBalance.get("Peter") < 5_000) {
            return startingBalance;
        }
        int halfMoneyPeter = startingBalance.get("Peter") / 2;

        startingBalance.put("Paul", startingBalance.get("Paul") + halfMoneyPeter);
        startingBalance.put("Peter", startingBalance.get("Peter") - halfMoneyPeter);

        return startingBalance;
    }

}
