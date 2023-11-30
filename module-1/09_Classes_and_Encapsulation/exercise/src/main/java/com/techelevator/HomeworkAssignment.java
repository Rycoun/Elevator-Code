package com.techelevator;

public class HomeworkAssignment {
    private int earnedMarks;
    private final int possibleMarks;
    private final String submitterName;

    public HomeworkAssignment(int possibleMarks, String subName) {
        this.possibleMarks = possibleMarks;
        this.submitterName = subName;

    }

    public int getEarnedMarks() {
        return earnedMarks;
    }
    public void  setEarnedMarks(int earned) {
        this.earnedMarks = earned;
    }

    public int getPossibleMarks() {
        return possibleMarks;
    }

    public String getSubmitterName() {
    return submitterName;
    }

    public String getLetterGrade() {
       double score = ((double) earnedMarks / possibleMarks) * 100;
        if (score > 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
