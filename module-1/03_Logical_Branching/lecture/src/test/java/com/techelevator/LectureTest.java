package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

/**
 * MorningExercisesTest
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LectureTest {

    private Lecture exercises = new Lecture();

    @Test
    public void test01_testReturnNotOne() {
        assertNotEquals("Value returned shouldn't be one", 1, exercises.returnNotOne());
    }

    @Test
    public void test02_testReturnNotHalf() {
        assertNotEquals("Value returned shouldn't be 0.5", 0.5, exercises.returnNotHalf(), 0.001);
    }

    @Test
    public void test03_testReturnName() {
        assertNotNull("Value returned should be your name", exercises.returnName());
    }

    @Test
    public void test04_testReturnDoubleOfTwo() {
        assertTrue("Value returned should be a double", Double.class.isInstance(exercises.returnDoubleOfTwo()));
        assertEquals("Value returned should still equal two", 2.0, exercises.returnDoubleOfTwo(), 0);
    }

    @Test
    public void test05_testReturnNameOfLanguage() {
        Object result = exercises.returnNameOfLanguage();
        assertEquals("Value should equal the name of the programming language you're learning", "Java", result);
    }

    @Test
    public void test06_testReturnTrueFromIf() {
        assertTrue("If statement should return true", exercises.returnTrueFromIf());
    }

    @Test
    public void test07_testReturnTrueWhenOneEqualsOne() {
        assertTrue("If statement should return true", exercises.returnTrueWhenOneEqualsOne());
    }

    @Test
    public void test08_testReturnWhenGreaterThanFive() {
        assertTrue("We should return true when parameter is greater than five",
                exercises.returnTrueWhenGreaterThanFive(6));
        assertFalse("We should return false when parameter is five", exercises.returnTrueWhenGreaterThanFive(5));
        assertFalse("We should return false when parameter is smaller than five",
                exercises.returnTrueWhenGreaterThanFive(4));
    }

    @Test
    public void test09_testReturnWhenGreaterThanFiveInOneLine() {
        assertTrue("We should return true when parameter is greater than five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(6));
        assertFalse("We should return false when parameter is five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(5));
        assertFalse("We should return false when parameter is smaller than five",
                exercises.returnTrueWhenGreaterThanFiveInOneLine(4));
    }

    @Test
    public void test10_testReturnNumberAfterAddThreeAndAddFive() {
        assertEquals("We should add three and five when both are true", 9,
                exercises.returnNumberAfterAddThreeAndAddFive(1, true, true));
        assertEquals("We should add three when passed true false", 4,
                exercises.returnNumberAfterAddThreeAndAddFive(1, true, false));
        assertEquals("We should add five when passed false true", 6,
                exercises.returnNumberAfterAddThreeAndAddFive(1, false, true));
        assertEquals("We should return the original number when both are false", 1,
                exercises.returnNumberAfterAddThreeAndAddFive(1, false, false));
    }

    @Test
    public void test11_testReturnFizzIfThree() {
        assertEquals("Fizz", exercises.returnFizzIfThree(3));
        assertEquals("", exercises.returnFizzIfThree(6));
    }

    @Test
    public void test12_testReturnFizzIfThreeUsingTernary() {
        assertEquals("Fizz", exercises.returnFizzIfThreeUsingTernary(3));
        assertEquals("", exercises.returnFizzIfThreeUsingTernary(6));
    }

    @Test
    public void test13_testReturnFizzOrBuzzOrNothing() {
        assertEquals("Fizz", exercises.returnFizzOrBuzzOrNothing(3));
        assertEquals("Buzz", exercises.returnFizzOrBuzzOrNothing(5));
        assertEquals("", exercises.returnFizzOrBuzzOrNothing(15));
    }

    @Test
    public void test14_testReturnAdultOrMinor() {
        assertEquals("Adult", exercises.returnAdultOrMinor(20));
        assertEquals("Adult", exercises.returnAdultOrMinor(18));
        assertEquals("Minor", exercises.returnAdultOrMinor(17));
    }

    @Test
    public void test15_testReturnAdultOrMinorAgain() {
        assertEquals("Adult", exercises.returnAdultOrMinorAgain(20));
        assertEquals("Adult", exercises.returnAdultOrMinorAgain(18));
        assertEquals("Minor", exercises.returnAdultOrMinorAgain(17));
    }

    @Test
    public void test16_testReturnAdultOrMinorOrTeen() {
        assertEquals("Adult", exercises.returnAdultOrMinorOrTeen(18));
        assertEquals("Teen", exercises.returnAdultOrMinorOrTeen(17));
        assertEquals("Teen", exercises.returnAdultOrMinorOrTeen(13));
        assertEquals("Minor", exercises.returnAdultOrMinorOrTeen(12));
    }

    @Test
    public void test17_testReturnPizzaCost() {
        assertEquals(9.99, exercises.returnPizzaCost('s', 1), 0.001);
        assertEquals(10.99, exercises.returnPizzaCost('m', 1), 0.001);
        assertEquals(11.99, exercises.returnPizzaCost('s', 3), 0.001);
        assertEquals(10.99, exercises.returnPizzaCost('l', 0), 0.001);
        assertEquals(13.99, exercises.returnPizzaCost('l', 4), 0.001);
    }
}
