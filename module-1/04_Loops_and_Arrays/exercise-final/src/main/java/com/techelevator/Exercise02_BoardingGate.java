package com.techelevator;

public class Exercise02_BoardingGate {

    /*
    Local Jetways is a regional airline operating at local airports.
    They use a basic passenger manifest to represent seat occupancy on their plane.
        Each passenger seat is represented as an element in a boolean array.
        A value of AVAILABLE (true) indicates that seat is currently available.
        A value of OCCUPIED (false) indicates the seat is not available.
     */
    private final boolean AVAILABLE = true;
    private final boolean OCCUPIED = false;

    /*
    A nearby airport has an incoming flight from Local Jetways. As the passengers disembark, the gate
    attendant's first responsibility is to generate a new seating chart with each seat initially available.

    Implement the logic to generate a seating chart using the required number of seats. Make sure to indicate
    that each seat is initially available (TRUE).

    Note: The number of seats is guaranteed not to be negative.

    Examples:
    generateSeatingChart(7) → [AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE]
    generateSeatingChart(5) → [AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE]
    generateSeatingChart(2) → [AVAILABLE, AVAILABLE]
     */
    public boolean[] generateSeatingChart(int numberOfSeats) {
        boolean[] seatingChart = new boolean[numberOfSeats];

        for (int i = 0; i < seatingChart.length; i++) {
            seatingChart[i] = AVAILABLE;
        }

        return seatingChart;
    }

    /*
    Once passengers begin boarding the plane, gate attendants need a way to determine how many available
    seats there are on the plane.

    Using the array provided, implement the logic to count the number of available seats in the seating chart.

    Examples:
    getAvailableSeatCount([AVAILABLE, OCCUPIED, OCCUPIED, OCCUPIED]) → 1
    which is the same as:
    getAvailableSeatCount([true, false, false, false]) → 1

    getAvailableSeatCount([OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED, OCCUPIED]) → 0
    getAvailableSeatCount([AVAILABLE, AVAILABLE, AVAILABLE, OCCUPIED]) → 3
    getAvailableSeatCount([]) → 0
     */
    public int getAvailableSeatCount(boolean[] seatingChart) {
        int count = 0;

        for (int i = 0; i < seatingChart.length; i++) {
            if (seatingChart[i] == AVAILABLE) {
                count++;
            }
        }

        return count;
    }

    /*
    The crew determined that it would be nice to know how many rows on the plane are at full occupancy.
    Each row has three seats and a row is at full occupancy if all three seats have someone sitting in them.

    Using the boolean array, implement the logic to count the number of full rows on the plane.
    Note: A new row starts at every third element. For example, row one begins with index 0, row two begins with index 3, and so on.

    Examples:
    getNumberOfFullRows([OCCUPIED, OCCUPIED, OCCUPIED, AVAILABLE, AVAILABLE, AVAILABLE]) → 1
    getNumberOfFullRows([AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE, AVAILABLE]) → 0
    getNumberOfFullRows([OCCUPIED, AVAILABLE, AVAILABLE, OCCUPIED, AVAILABLE, AVAILABLE]) → 0
     */
    public int getNumberOfFullRows(boolean[] seatingChart) {
        int fullRows = 0;
        int streak = 0;

        for (int i = 0; i < seatingChart.length; i++) {
            if (i % 3 == 0) { //new row, start over
                streak = 0;
            }

            if (seatingChart[i] == OCCUPIED) { //streak continues
                streak++;
            } else { //streak is over
                streak = 0;
                continue;
            }

            if (streak == 3) {
                fullRows += 1;
            }
        }

        return fullRows;
    }


}
