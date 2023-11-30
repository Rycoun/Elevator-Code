package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LectureTest {

    Lecture exercises = new Lecture();

    @Test
    public void test01_testReturnArray() {
        assertArrayEquals(new int[] { 80, 8080, 443 }, exercises.returnArray());
    }

    @Test
    public void test02_testReturnFirstElement() {
        assertEquals("That's not the first element in that array", 80, exercises.returnFirstElement());
    }

    @Test
    public void test03_testReturnLastElement() {
        assertEquals("That's not the last element", 443, exercises.returnLastElement());
    }

    @Test
    public void test04_testReturnFirstElementOfParam() {
        assertEquals("That's not the first element from {5, 10, 15}", 5,
                exercises.returnFirstElementOfParam(new int[] { 5, 10, 15 }));
        assertEquals("That's not the first element from {10, 20, 30, 40, 50}", 10,
                exercises.returnFirstElementOfParam(new int[] { 10, 20, 30, 40, 50 }));
    }

    @Test
    public void test05_testReturnLastElementOfParam() {
        assertEquals("That's not the last element from {5, 10, 15}", 15,
                exercises.returnLastElementOfParam(new int[] { 5, 10, 15 }));
        assertEquals("That's not the last element from {10, 20, 30, 40, 50}", 50,
                exercises.returnLastElementOfParam(new int[] { 10, 20, 30, 40, 50 }));
    }

    @Test
    public void test06_testReturnVariableFromBlock() {
        assertEquals(15, exercises.returnVariableFromBlock(3));
    }

    @Test
    public void test07_testReturnOperationInBlock() {
        assertTrue("Not true yet!", exercises.returnOperationInBlock());
    }

    @Test
    public void test08_testReturnVariableInScope() {
        assertEquals("Not that one, try again.", 5.0, exercises.returnInScopeVariable(), 0.001);
    }

    @Test
    public void test09_testReturnCounterFromLoop() {
        assertTrue("Not true yet!", exercises.returnCounterFromLoop());
    }

    @Test
    public void test10_testReturnCorrectCount() {
        assertTrue("Not Correct Yet!", exercises.returnCorrectCount());
    }

    @Test
    public void test11_testReturnCorrectCountTimes() {
        assertTrue("Not Correct Yet!", exercises.returnCountCorrectTimes());
    }

    @Test
    public void test12_testReturnSumEveryOtherNumber() {
        assertTrue("Not Correct Yet!", exercises.returnSumEveryOtherNumber());
    }
}
