package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DateFashionTest {

    private DateFashion dateFashion;

    @Before
    public void set() {
        dateFashion = new DateFashion();
    }
    @Test
    public void get_a_table_you2_date8_returns_no() {
        int you = 2;
        int date = 8;

        int result = dateFashion.getATable(you, date);
        Assert.assertEquals(0, result);

    }

    @Test
    public void get_a_table_you0_date9_returns_no() {
        int you = 0;
        int date = 9;

        int result = dateFashion.getATable(you, date);
        Assert.assertEquals(0, result);

    }

    @Test
    public void get_a_table_you9_date0_returns_yes() {
        int you = 9;
        int date = 0;

        int result = dateFashion.getATable(you, date);
        Assert.assertEquals(0, result);

    }

    @Test
    public void get_a_table_you8_date8_returns_yes() {
        int you = 8;
        int date = 8;

        int result = dateFashion.getATable(you, date);
        Assert.assertEquals(2, result);

    }




}
