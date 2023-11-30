package com.techelevator;

public class Exercise04_HotelReservation {

    /*
    Innovator's Inn is a new hotel chain with two simple rates:
        $99.99 per night for stays of 1 or 2 nights
        $89.99 per night for stays of 3 nights or more
    The problems below ask you to implement the logic for determining a guest's total amount for their stay.
     */

    // You can use these constants in your solutions.
    private final double DAILY_RATE = 99.99;
    private final double DISCOUNT_RATE = 89.99;
    private final double PARKING_RATE = 25.0;
    private final double LATE_CHECKOUT_FEE = 20.0;
    private final int MINIMUM_NIGHTS_FOR_DISCOUNT_RATE = 3;

    /*
    Using the rates from above, implement the logic to determine the total amount based on 
    the number of nights a guest stays.

    Examples:
    calculateStayTotal(1) ➔ 99.99
    calculateStayTotal(2) ➔ 199.98
    calculateStayTotal(3) ➔ 269.97
     */
    public double calculateStayTotal(int numberOfNights) {
        double discount = (numberOfNights <= 2) ? DAILY_RATE : DISCOUNT_RATE;
        return numberOfNights * discount;
    }

    /*
    The owners of Innovator's Inn offer parking at an additional cost of $25.00 per night.
    Calculate the stay total based on the number of nights (int) 
    and on whether the guest requires parking (boolean).

    Examples:
    calculateStayTotal(2, false) ➔ 199.98
    calculateStayTotal(2, true) ➔ 249.98
    calculateStayTotal(3, false) ➔ 269.97
    calculateStayTotal(3, true) ➔ 344.97
     */
    public double calculateStayTotal(int numOfTotalNights, boolean includesParking) {
        double discount = (numOfTotalNights <= 2) ? DAILY_RATE : DISCOUNT_RATE;
        double cost = numOfTotalNights * discount;
        if (includesParking) {
            cost += numOfTotalNights * PARKING_RATE;
        }
        return cost;
    }

    /*
    Innovator's Inn offers late checkout—but it comes at a price.
    A guest can reserve a late checkout for an additional fee of $20. 
    Calculate the stay total given the number of nights (int), 
    whether they require parking (boolean), and whether they require a late checkout (boolean). 

    Examples:
    calculateStayTotal(2, false, false) ➔ 199.98
    calculateStayTotal(2, false, true) ➔ 219.98
    calculateStayTotal(2, true, false) ➔ 249.98
    calculateStayTotal(2, true, true) ➔ 269.98
    calculateStayTotal(3, false, false) ➔ 269.97
    calculateStayTotal(3, false, true) ➔ 289.97
    calculateStayTotal(3, true, false) ➔ 344.97
    calculateStayTotal(3, true, true) ➔ 364.97
     */
    public double calculateStayTotal(int numOfTotalNights, boolean includesParking, boolean includesLateCheckout) {
        double discount = (numOfTotalNights <= 2) ? DAILY_RATE : DISCOUNT_RATE;
        double cost = numOfTotalNights * discount;
        if (includesParking) {
            cost += numOfTotalNights * PARKING_RATE;
        }
        if (includesLateCheckout) {
            cost += LATE_CHECKOUT_FEE;
        }
        return cost;
    }}