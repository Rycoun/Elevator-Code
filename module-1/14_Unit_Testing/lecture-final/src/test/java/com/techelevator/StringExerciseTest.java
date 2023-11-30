package com.techelevator;

import com.techelevator.strings.StringExercise;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringExerciseTest {
    private StringExercise sut;

    @Before
    public void setup() {
        sut = new StringExercise();
    }

    @Test
    public void hasBad_returns_false_given_null() {
        // arrange

        // act
        boolean hasBad = sut.hasBad(null);

        // assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_empty_string() {
        // arrange

        // act
        boolean hasBad = sut.hasBad("");

        // assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_false_given_xxbad() {
        // arrange

        // act
        boolean hasBad = sut.hasBad("xxbad");

        // assert
        Assert.assertFalse(hasBad);
    }

    @Test
    public void hasBad_returns_true_given_xbad() {
        // arrange

        // act
        boolean hasBad = sut.hasBad("xbad");

        // assert
        Assert.assertTrue(hasBad);
    }

    @Test
    public void hasBad_returns_true_given_bad() {
        // arrange

        // act
        boolean hasBad = sut.hasBad("bad");

        // assert
        Assert.assertTrue(hasBad);
    }

}
