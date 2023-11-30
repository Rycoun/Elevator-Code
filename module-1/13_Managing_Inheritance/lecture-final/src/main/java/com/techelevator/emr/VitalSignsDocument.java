package com.techelevator.emr;

public class VitalSignsDocument extends Document {
    private String bloodPressure;
    private int heartRateBpm;

    public VitalSignsDocument(String whoWasDocumenting) {
        super(whoWasDocumenting);
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRateBpm() {
        return heartRateBpm;
    }

    public void setHeartRateBpm(int heartRateBpm) {
        this.heartRateBpm = heartRateBpm;
    }

    @Override
    public void saveDocumentQuestions() {
        // save blood pressure and heart rate to the database
    }
}
