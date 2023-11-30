package com.techelevator;

import java.util.LinkedList;
import java.util.*;

public class Queues {

    /*
        1) Given 2 lists, fill a queue by taking 1 from each list.

        2) Given an integer called numberOfShirts, create queues of Small, Medium, and Large t-shirts.
     */
    public static void main(String[] args) {
        Queue<String> myQueue = new LinkedList<>();

        myQueue.offer("Sam"); // end of queue
        myQueue.offer("Walt"); // after Sam

        String isNext = myQueue.poll();// removes sam and return sam

        isNext = myQueue.peek(); // take from front but dont remove

        // process the queue
        while (myQueue.size() > 0) {
            isNext = myQueue.poll();

        }




    }

}
