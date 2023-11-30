package com.techelevator;

public class Exercise06_RaceDay {

    /*
     * Registration for your company's annual 10K is underway.
     * Participants are assigned to a block based on their age (int) and whether
     * they registered early (boolean).
     * 
     * Return a participant's block according to the following criteria:
     * If they are >= 18 and registered early, they join the first block.
     * If they are >= 18 and did not register early, they join the second block.
     * If they are < 18, they join the third block.
     * 
     * Given a runner's age (int) and early-registration status (boolean), return
     * their assigned race block:
     * 
     * Examples:
     * determineRaceBlock(17, false) ➔ 3
     * determineRaceBlock(17, true) ➔ 3
     * determineRaceBlock(18, false) ➔ 2
     * determineRaceBlock(18, true) ➔ 1
     * determineRaceBlock(30, false) ➔ 2
     * determineRaceBlock(30, true) ➔ 1
     */
    public int determineRaceBlock(int age, boolean isEarlyRegistration) {

        if (age < 18) {
            return 3;
        }
        if (isEarlyRegistration) {
            return 1;
        } else {
            return 2;
        }
    }

    /*
     * The race organizers need to assign each participant a bib number. The bib
     * number is either their registration number, or their registration number + 1000,
     * depending on the following conditions:
     * 
     * If they are >= 18 and registered early their race bib number is their
     * registration number + 1000.
     * If they are < 18, or >= 18 and did not register early, their race bib
     * number is their registration number.
     * 
     * Given a participant's age (int), their registration number (int), and whether
     * they registered early (boolean), return their race bib number (int).
     * 
     * Examples:
     * getBibNumber(17, 500, false) ➔ 500
     * getBibNumber(17, 500, true) ➔ 500
     * getBibNumber(18, 600, false) ➔ 600
     * getBibNumber(18, 600, true) ➔ 1600
     * getBibNumber(30, 700, false) ➔ 700
     * getBibNumber(30, 700, true) ➔ 1700
     */
    public int getBibNumber(int age, int registrationNumber, boolean isEarlyRegistration) {

        if (age >= 18 && isEarlyRegistration) {
            return registrationNumber + 1000;
        }

        return registrationNumber;
    }

    /*
     * As the race approaches full capacity, organizers need to adjust the bib
     * numbering system.
     * 
     * Given a participant's age (int), their registration number (int), and
     * whether they registered early (boolean), return their race bib number (int).
     * Apply the same rules as above with one exception. If a runner did not
     * register early and their registration number is > 1000, return -1 to indicate
     * that there are no more spots left.
     * 
     * getConfirmedBibNumber(17, 500, false) ➔ 500
     * getConfirmedBibNumber(17, 500, true) ➔ 500
     * getConfirmedBibNumber(18, 600, false) ➔ 600
     * getConfirmedBibNumber(18, 600, true) ➔ 1600
     * getConfirmedBibNumber(30, 700, false) ➔ 700
     * getConfirmedBibNumber(30, 700, true) ➔ 1700
     * getConfirmedBibNumber(30, 1001, false) ➔ -1
     * getConfirmedBibNumber(30, 1001, true) ➔ 2001
     */
    public int getConfirmedBibNumber(int age, int registrationNumber, boolean isEarlyRegistration) {

        if (age >= 18) {
            if (isEarlyRegistration) {
                return registrationNumber + 1000;
            } else if (registrationNumber <= 1000) {
                return registrationNumber;
            } else {
                return -1; // spot not available
            }
        } else { // age < 18
            return registrationNumber;
        }
    }
}
