package com.techelevator.emr;

import java.time.LocalDateTime;

public abstract class Document {
    private String whoWasDocumenting;
    private LocalDateTime lastUpdated;

    public Document(String whoWasDocumenting) {
        this.whoWasDocumenting = whoWasDocumenting;
    }

    public void save() {
        // go out to our database and save a record
        // storing all fields in this object

        // save the specific questions
        saveDocumentQuestions();
    }

    public abstract void saveDocumentQuestions();
}
