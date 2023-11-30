package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FrontTimesTest {

    private FrontTimes frontTimes;

    @Before
    public void set() {
        frontTimes = new FrontTimes();

    }


    @Test
    public void short_string_one_copy_returns_short_string() {
        String sut = "Abc";
        int a = 1;

        String result = frontTimes.generateString(sut, a);
        Assert.assertEquals("Abc", result);
    }
    @Test
    public void Long_string_one_copy_returns_short_string() {
        String sut = "Chocolate";
        int a = 1;

        String result = frontTimes.generateString(sut, a);
        Assert.assertEquals("Cho", result);
    }
    @Test
    public void short_string_lots_of_choices_returns_lots_of_strings() {
        String sut = "Abc";
        int a = 3;

        String result = frontTimes.generateString(sut, a);
        Assert.assertEquals("AbcAbcAbc", result);
    }
    @Test
    public void short_string_null_returns_empty() {
        String sut = null;
        int a = 2;

        String result = frontTimes.generateString(sut, a);
        Assert.assertEquals("", result);
    }




}
