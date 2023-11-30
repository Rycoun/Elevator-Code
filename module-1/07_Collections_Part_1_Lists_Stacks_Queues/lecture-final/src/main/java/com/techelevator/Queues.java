package com.techelevator;

import java.util.Queue;
import java.util.LinkedList;

public class Queues {

    /*
        1) Given 2 lists, fill a queue by taking 1 from each list.

        2) Given an integer called numberOfShirts, create queues of Small, Medium, and Large t-shirts.
     */

    public static void main(String[] args) {

        // Create a queue
        Queue<String> myQueue = new LinkedList<>();

        myQueue.offer("Sam"); // add to the end of the queue
        myQueue.offer("Walt"); // add to the end of the queue

        String whoIsNext = myQueue.poll(); // Remove "Sam" from the queue and return "Sam"

        whoIsNext = myQueue.peek(); // Take from the front, but don't remove them

        // process the entire queue
        while (myQueue.size() > 0) { // alternative: !myQueue.isEmpty()
            whoIsNext = myQueue.poll();

            // do something with whoIsNext
            System.out.println("Next up: " + whoIsNext);
        }



    }
}
