package com.techelevator.emr;

import javax.print.Doc;
import java.time.LocalDateTime;

public abstract class Document {
    private String whoWasDoc;
    private LocalDateTime lastUpdated;
    private Document(String whoWasDoc) {
        this.whoWasDoc = whoWasDoc;

    }

    public void save() {
        // go out to database and save a record, storing all fields in this object
        saveDocQuestions();
    }
    public abstract void saveDocQuestions();



}
