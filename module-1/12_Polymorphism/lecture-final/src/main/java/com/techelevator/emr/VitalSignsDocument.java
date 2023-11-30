package com.techelevator.emr;

public class VitalSignsDocument extends Document {
    private String bloodPressure;
    private int heartRateBPM;

    public VitalSignsDocument(String whoWasDoc) {
        super(whoWasDoc);
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getHeartRateBPM() {
        return heartRateBPM;
    }

    public void setHeartRateBPM(int heartRateBPM) {
        this.heartRateBPM = heartRateBPM;
    }
    public void save() {
        saveDocQuestions();
    }
    public abstract void SaveDocQuestions() {
        // save blood pressure and heart rate to database

    }
}
