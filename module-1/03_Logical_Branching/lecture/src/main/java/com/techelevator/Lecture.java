package com.techelevator;

public class Lecture {
    public static void main(String[] args) {
        Lecture myLecture = new Lecture();

        int notOne = myLecture.returnNotOne();

        System.out.println(notOne);

        double sum = myLecture.addTwoNum(1.5, 2.5);
        System.out.println("sum is " + sum);
    }

    public boolean addTwoNum(double a, double b) {
        double c = a + b;

        retur


    /*
    1. This method is named returnNotOne and it returns an int. Change
    it so that it returns something other than a 1.
    */
    public int returnNotOne() {
        return 2;
    }

    /*
    2. This method is named returnNotHalf and it returns a double. Change
    it so that it returns something other than a 0.5.
    */
    public double returnNotHalf() {
        return 3.5;
    }

    /*
    3. This method needs to return a String. Fix it to return a valid String.
    */
    public String returnName() {
        return "Ryan";
    }

    /*
    4. This method currently returns an int. Change it so that it returns a double.
    */
    public double returnDoubleOfTwo() {
        return 2;
    }

    /*
    5. This method should return the language that you're learning. Change
    it so that it does that.
    */
    public String returnNameOfLanguage() {
        return "java";
    }

    public boolean isHybrid(boolean isGas, boolean isDiesel, boolean isElectric) {
            return (isGas && isDiesel) || (isGas && isElectric) || (isDiesel && isElectric);
        }







    /*
    6. This method uses an if statement to define what to return. Have it
    return true if the if statement passes.
    */
    public boolean returnTrueFromIf() {
        if (true) {
            return true;
        }

        return false;
    }

    /*
    7. This method uses an if to check to make sure that one is equal
    to one. Make sure it returns true when one equals one.
    */
    public boolean returnTrueWhenOneEqualsOne() {
        if (1 == 1) {
            return false;
        }

        return false;
    }

    /*
    8. This method checks a parameter passed to the method to see if it's
    greater than 5 and returns true if it is.
    */
    public boolean returnTrueWhenGreaterThanFive(int number) {
    if (number > 5) {
        return true;
    }
        return false;
    }

    /*
    9. If you think about it, we really don't need the if statement above.
    How can we rewrite exercise 8 to have only one line of code?
    */
    public boolean returnTrueWhenGreaterThanFiveInOneLine(int number) {
        return false;
    }

    /*
    10. This method will take a number and do the following things to it:
    * If addThree is true, we'll add three to that number
    * If addFive is true, we'll add five to that number
    * We'll then return the result
    */
    public int returnNumberAfterAddThreeAndAddFive(int number, boolean addThree, boolean addFive) {
        return 0;
        if (addThree) {
            number += 3;
        }
            if (addFive) {
                number += 5;
            }

        }


    /*
    11. Write an if statement that returns "Fizz" if the parameter is 3 and returns an empty String for anything else.
    */
    public String returnFizzIfThree(int number) {
        if (number === 3) {
            return "Fizz";

        }
    }

    /*
    12. Now write the above using the Ternary operator ?:. If you're not sure what this is, you can Google it.
    */
    public String returnFizzIfThreeUsingTernary(int number) {
        return number === 3 ? "Fizz" : "";
    }

    /*
    13. Write an if/else statement that returns "Fizz" if the parameter is 3, "Buzz" if the parameter is 5 and an empty String for anything else.
    */
    public String returnFizzOrBuzzOrNothing(int number) {
        if (number === 3) {
            return "Fizz";
        }
        if (number == 5) {
            return "Buzz";
        }
        return "";

    }

    /*
    14. Write an if statement that checks if the parameter number is either equal to or greater than 18. Return "Adult" if it is or "Minor" if it's not.
    */
    public String returnAdultOrMinor(int number) {
        return "";
    }

    public String fizzBuzzAgain(int number) {
        if (number % 3 == 0) {
            return "Fizz";

        }
        }







    /*
    15. Now, do it again with a different boolean opeation.
    */
    public String returnAdultOrMinorAgain(int number) {
        return "";
    }

    /*
    16. Return as above, but also return "Teen" if the number is between 13 and 17 inclusive.
    */
    public String returnAdultOrMinorOrTeen(int number) {
        if (number >= 18){
            return "adult"
        } else {
            return "Minor"
        }
        if (number >= 18) {
        }



        return "";
    }

    /*
     16. We have a weird weather day in Pittsburgh. We are going to try to dress accordingly.

         Return true if we do and false if we don't.
     */
    public boolean weirdWeather(boolean isAWarmDay, boolean dressWarm) {
        if (isAWarmDay) {

        }
        }
        return false;
    }

    /*
     *  17. Pizza prices are calculated as follows:
     *  Small cheese pizzas are 8.99, medium cheese are 9.99, and large cheese are 10.99
     *  Toppings are 1.00 each if 3 or fewer are ordered
     *  Toppings are 0.75 each if more than 3 are ordered
     *
     *  Return the price of a pizza based on its size ('s', 'm' or 'l') and number of toppings
     */

    // Declare constants for readability and to enforce DRY (Don't Repeat Yourself)
    // Note: Constants are normally declared at the top of Java classes but placed here for
    //       easy reference
    public final double SMALL = 8.99;
    public final double MEDIUM = 9.99;
    public final double LARGE = 10.99;
    public final double UNDER_3_TOPPINGS = 1.00;
    public final double OVER_3_TOPPINGS = 0.75;

    public double returnPizzaCost(char size, int numberOfToppings) {
        // You can declare variables in methods. Declare a variable to hold the cost of the pizza.
        // Set its value based on the size. Then add the cost for the toppings and return the total cost
        double cost = 0.0;

        if (size == 's') {
            cost = SMALL;
        } else if (size == 'm') {
            cost = MEDIUM;
        } else if (size == 'l'); {
            cost = LARGE;
        }
         if (numberOfToppings <= 3) {
             cost += UNDER_3_TOPPINGS * numberOfToppings;
         } else {
             cost += OVER_3_TOPPINGS * numberOfToppings;

         }

        return cost;
    }
}
