package com.techelevator;

import com.techelevator.strings.StringExercise;
import org.junit.Assert;
import org.junit.Test;

public class StringExerciseTest {



    @Test
    public void hasBad_returns_false_given_null() {
        StringExercise sut = new StringExercise();

        boolean hasBad = sut.hasBad(null);

        Assert.assertFalse(hasBad);

    }

    @Test
    public void hasBad_returns_false_given_empty_String() {
        StringExercise sut = new StringExercise();

        boolean hasBad = sut.hasBad("");

        Assert.assertFalse(hasBad);


    }

    @Test
    public void hasBad_returns_false_given_xxbad() {
        StringExercise sut = new StringExercise();

        boolean hasBad = sut.hasBad("xxbad");

        Assert.assertFalse(hasBad);

    }

    @Test
    public void hasBad_returns_false_given_xbad() {
        StringExercise sut = new StringExercise();

        boolean hasBad = sut.hasBad("xbad");

        Assert.assertTrue(hasBad);

    }

    @Test
    public void hasBad_returns_false_given_bad() {
        StringExercise sut = new StringExercise();

        boolean hasBad = sut.hasBad("bad");

        Assert.assertTrue(hasBad);

    }
}