package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CigarPartyTest {

    private CigarParty cigarParty;
    @Before
    public void set() {
        cigarParty = new CigarParty();
    }


    @Test
    public void haveParty_weekend_cigar_above_range_return_true() {
        int cig = 70;
        boolean isWeekend = true;
        boolean result = cigarParty.haveParty(cig, isWeekend);

        Assert.assertTrue(result);
    }

    @Test
    public void haveParty_weekday_cigar_in_range_return_true() {
        int cig = 50;
        boolean isWeekend = false;
        boolean result = cigarParty.haveParty(cig, isWeekend);

        Assert.assertTrue(result);
    }

    @Test
    public void haveParty_weekday_cigar_above_below_range_return_false() {
        int cig = 30;
        boolean isWeekend = false;
        boolean result = cigarParty.haveParty(cig, isWeekend);

        Assert.assertFalse(result);
    }





}
