package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringBitsTest {
    private StringBits stringBits;
    @Before
    public void set() {
        stringBits = new StringBits();
    }

    @Test
    public void short_string_returns_only_first() {
        String a = "Hi";
        String expecting = "H";
        String total = stringBits.getBits(a);
        Assert.assertEquals(expecting, total);


    }
    @Test
    public void plain_string_returns_only_every_other() {
        String a = "Hello";
        String expecting = "Hlo";
        String total = stringBits.getBits(a);
        Assert.assertEquals(expecting, total);


    }
    @Test
    public void null_string_returns_empty() {
        String a = null;
        String expecting = "";
        String total = stringBits.getBits(a);
        Assert.assertEquals(expecting, total);


    }
}
