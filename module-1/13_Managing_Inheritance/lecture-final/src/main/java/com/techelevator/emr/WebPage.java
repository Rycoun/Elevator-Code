package com.techelevator.emr;

public class WebPage {
    public static void main(String[] args) {

        VitalSignsDocument vitalSignsDocument = new VitalSignsDocument("Walt");

        vitalSignsDocument.setBloodPressure("120/80");

        vitalSignsDocument.save();
    }
}
