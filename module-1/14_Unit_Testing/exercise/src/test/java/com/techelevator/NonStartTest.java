package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.PrimitiveIterator;

public class NonStartTest {
    private NonStart nonStart;
    @Before
    public void set() {
        nonStart = new NonStart();

    }
    @Test
    public void both_empty_returns_empty() {
        String f = "";
        String g = "";
        String expecting = "";
        String total = nonStart.getPartialString(f, g);
        Assert.assertEquals(expecting, total);
    }

    @Test
    public void returns_string_without_first() {
        String f = "Hello";
        String g = "There";
        String expecting = "ellohere";
        String total = nonStart.getPartialString(f, g);
        Assert.assertEquals(expecting, total);
    }
    @Test
    public void first_string_is_filled_while_other_is_empty() {
        String f = "java";
        String g = "";
        String expecting = "ava";
        String total = nonStart.getPartialString(f, g);
        Assert.assertEquals(expecting, total);
    }


}
