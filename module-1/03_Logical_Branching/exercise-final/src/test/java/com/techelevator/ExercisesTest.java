package com.techelevator;

import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExercisesTest {

	@Test
	public void Exercise00_isRainExpected() {
		Exercise00_GettingStarted exercise = new Exercise00_GettingStarted();
		// Warm cases
		assertEquals("isRainExpected(true, 45) should return true", true, exercise.isRainExpected(true, 45));
		assertEquals("isRainExpected(false, 45) should return false", false, exercise.isRainExpected(false, 45));
		//Cold cases
		assertEquals("isRainExpected(true, 16) should return false", false, exercise.isRainExpected(true, 16));
		assertEquals("isRainExpected(false, 16) should return false", false, exercise.isRainExpected(false, 16));
		// Edge cases
		assertEquals("isRainExpected(true, 32) should return false", false, exercise.isRainExpected(true, 32));
		assertEquals("isRainExpected(false, 32) should return false", false, exercise.isRainExpected(false, 32));
	}

	@Test
	public void Exercise01_gradeTest_1() {
		Exercise01_TestGrading exercise = new Exercise01_TestGrading();
		assertTrue("gradeTestPassFail(100) should return true", exercise.gradeTestPassFail(100));
		assertTrue("gradeTestPassFail(90) should return true", exercise.gradeTestPassFail(90));
		assertTrue("gradeTestPassFail(70) should return true", exercise.gradeTestPassFail(70));
		assertFalse("gradeTestPassFail(69) should return false", exercise.gradeTestPassFail(69));
		assertFalse("gradeTestPassFail(60) should return false", exercise.gradeTestPassFail(60));
		assertFalse("gradeTestPassFail(0) should return false", exercise.gradeTestPassFail(0));
	}

	@Test
	public void Exercise01_gradeTest_2() {
		Exercise01_TestGrading exercise = new Exercise01_TestGrading();
		assertEquals("gradeTestNumeric(100) should return 3", 3, exercise.gradeTestNumeric(100));
		assertEquals("gradeTestNumeric(95) should return 3", 3, exercise.gradeTestNumeric(95));
		assertEquals("gradeTestNumeric(90) should return 3", 3, exercise.gradeTestNumeric(90));
		assertEquals("gradeTestNumeric(89) should return 2", 2, exercise.gradeTestNumeric(89));
		assertEquals("gradeTestNumeric(70) should return 2", 2, exercise.gradeTestNumeric(70));
		assertEquals("gradeTestNumeric(50) should return 2", 2, exercise.gradeTestNumeric(50));
		assertEquals("gradeTestNumeric(49) should return 1", 1, exercise.gradeTestNumeric(49));
		assertEquals("gradeTestNumeric(37) should return 1", 1, exercise.gradeTestNumeric(37));
		assertEquals("gradeTestNumeric(25) should return 1", 1, exercise.gradeTestNumeric(25));
		assertEquals("gradeTestNumeric(24) should return 0", 0, exercise.gradeTestNumeric(24));
		assertEquals("gradeTestNumeric(12) should return 0", 0, exercise.gradeTestNumeric(12));
		assertEquals("gradeTestNumeric(1) should return 0", 0, exercise.gradeTestNumeric(1));
		assertEquals("gradeTestNumeric(0) should return 0", 0, exercise.gradeTestNumeric(0));
	}

	@Test
	public void Exercise01_gradeTest_3() {
		Exercise01_TestGrading exercise = new Exercise01_TestGrading();
		assertEquals("gradeTestLetter(100) should return A", 'A', exercise.gradeTestLetter(100));
		assertEquals("gradeTestLetter(95) should return A", 'A', exercise.gradeTestLetter(95));
		assertEquals("gradeTestLetter(90) should return A", 'A', exercise.gradeTestLetter(90));
		assertEquals("gradeTestLetter(89) should return B", 'B', exercise.gradeTestLetter(89));
		assertEquals("gradeTestLetter(85) should return B", 'B', exercise.gradeTestLetter(85));
		assertEquals("gradeTestLetter(80) should return B", 'B', exercise.gradeTestLetter(80));
		assertEquals("gradeTestLetter(79) should return C", 'C', exercise.gradeTestLetter(79));
		assertEquals("gradeTestLetter(74) should return C", 'C', exercise.gradeTestLetter(74));
		assertEquals("gradeTestLetter(70) should return C", 'C', exercise.gradeTestLetter(70));
		assertEquals("gradeTestLetter(69) should return D", 'D', exercise.gradeTestLetter(69));
		assertEquals("gradeTestLetter(65) should return D", 'D', exercise.gradeTestLetter(65));
		assertEquals("gradeTestLetter(60) should return D", 'D', exercise.gradeTestLetter(60));
		assertEquals("gradeTestLetter(59) should return F", 'F', exercise.gradeTestLetter(59));
		assertEquals("gradeTestLetter(39) should return F", 'F', exercise.gradeTestLetter(39));
		assertEquals("gradeTestLetter(25) should return F", 'F', exercise.gradeTestLetter(25));
		assertEquals("gradeTestLetter(1) should return F", 'F', exercise.gradeTestLetter(1));
		assertEquals("gradeTestLetter(0) should return F", 'F', exercise.gradeTestLetter(0));
	}

	@Test
	public void Exercise02_canDrive_1() {
		Exercise02_CanDrive exercise = new Exercise02_CanDrive();

		assertTrue("canDrive(true, true) should return true", exercise.canDrive(true, true));
		assertFalse("canDrive(true, false) should return false", exercise.canDrive(true, false));
		assertFalse("canDrive(false, true) should return false", exercise.canDrive(false, true));
		assertFalse("canDrive(false, false) should return false", exercise.canDrive(false, false));
	}

	@Test
	public void Exercise02_canDrive_2() {
		Exercise02_CanDrive exercise = new Exercise02_CanDrive();

		assertTrue("canDrive(true, true, 42) should return true", exercise.canDrive(true, true, 42));
		assertTrue("canDrive(true, true, 21) should return true", exercise.canDrive(true, true, 21));
		assertFalse("canDrive(true, true, 20) should return false", exercise.canDrive(true, true, 20));
		assertFalse("canDrive(true, false, 42) should return false", exercise.canDrive(true, false, 42));
		assertFalse("canDrive(false, true, 42) should return false", exercise.canDrive(false, true, 42));
		assertFalse("canDrive(false, false, 42) should return false", exercise.canDrive(false, false, 42));
	}

	@Test
	public void Exercise02_canDrive_3() {
		Exercise02_CanDrive exercise = new Exercise02_CanDrive();

		assertTrue("canDrive(true, true, 42, true) should return true", exercise.canDrive(true, true, 42, true));
		assertTrue("canDrive(true, true, 18, true) should return true", exercise.canDrive(true, true, 18, true));
		assertTrue("canDrive(true, true, 21, false) should return true", exercise.canDrive(true, true, 21, false));
		assertFalse("canDrive(true, true, 20, false) should return false", exercise.canDrive(true, true, 20, false));
		assertFalse("canDrive(true, false, 42, true) should return false", exercise.canDrive(true, false, 42, true));
		assertFalse("canDrive(false, true, 42, true) should return false", exercise.canDrive(false, true, 42, true));
		assertFalse("canDrive(false, false, 42, true) should return false", exercise.canDrive(false, false, 42, true));
	}

	@Test
	public void Exercise03_calculateShipping_1() {
		Exercise03_ShippingTotal exercise = new Exercise03_ShippingTotal();

		assertEquals("calculateShippingRate(20) should return 0.5", 0.5, exercise.calculateShippingRate(20), 0.001);
		assertEquals("calculateShippingRate(39) should return 0.5", 0.5, exercise.calculateShippingRate(39), 0.001);
		assertEquals("calculateShippingRate(40) should return 0.5", 0.5, exercise.calculateShippingRate(40), 0.001);
		assertEquals("calculateShippingRate(41) should return 0.75", 0.75, exercise.calculateShippingRate(41), 0.001);
		assertEquals("calculateShippingRate(50) should return 0.75", 0.75, exercise.calculateShippingRate(50), 0.001);
	}

	@Test
	public void Exercise03_calculateShipping_2() {
		Exercise03_ShippingTotal exercise = new Exercise03_ShippingTotal();

		assertEquals("calculateShippingTotal(20) should return 10.0", 10.0, exercise.calculateShippingTotal(20), 0.001);
		assertEquals("calculateShippingTotal(39) should return 19.5", 19.5, exercise.calculateShippingTotal(39), 0.001);
		assertEquals("calculateShippingTotal(40) should return 20.0", 20.0, exercise.calculateShippingTotal(40), 0.001);
		assertEquals("calculateShippingTotal(41) should return 30.75", 30.75, exercise.calculateShippingTotal(41), 0.001);
		assertEquals("calculateShippingTotal(45) should return 33.75", 33.75, exercise.calculateShippingTotal(45), 0.001);
		assertEquals("calculateShippingTotal(50) should return 37.5", 37.5, exercise.calculateShippingTotal(50), 0.001);
	}

	@Test
	public void Exercise03_calculateShipping_3() {
		Exercise03_ShippingTotal exercise = new Exercise03_ShippingTotal();

		assertEquals("calculateShippingTotal(20, false) should return 10.0", 10.0, exercise.calculateShippingTotal(20, false), 0.001);
		assertEquals("calculateShippingTotal(20, true) should return 9.0", 9.0, exercise.calculateShippingTotal(20, true), 0.001);
		assertEquals("calculateShippingTotal(25, false) should return 12.5", 12.5, exercise.calculateShippingTotal(25, false), 0.001);
		assertEquals("calculateShippingTotal(25, true) should return 11.25", 11.25, exercise.calculateShippingTotal(25, true), 0.001);
		assertEquals("calculateShippingTotal(40, false) should return 20.0", 20.0, exercise.calculateShippingTotal(40, false), 0.001);
		assertEquals("calculateShippingTotal(40, true) should return 18.0", 18.0, exercise.calculateShippingTotal(40, true), 0.001);
		assertEquals("calculateShippingTotal(41, false) should return 30.75", 30.75, exercise.calculateShippingTotal(41, false), 0.001);
		assertEquals("calculateShippingTotal(41, true) should return 27.675", 27.675, exercise.calculateShippingTotal(41, true), 0.001);
		assertEquals("calculateShippingTotal(45, false) should return 33.75", 33.75, exercise.calculateShippingTotal(45, false), 0.001);
		assertEquals("calculateShippingTotal(45, true) should return 30.375", 30.375, exercise.calculateShippingTotal(45, true), 0.001);
	}

	@Test
	public void Exercise04_calculateStayTotal_1() {
		Exercise04_HotelReservation exercise = new Exercise04_HotelReservation();

		assertEquals("calculateStayTotal(1) should return 99.99", 99.99, exercise.calculateStayTotal(1), 0.001);
		assertEquals("calculateStayTotal(2) should return 199.98", 199.98, exercise.calculateStayTotal(2), 0.001);
		assertEquals("calculateStayTotal(3) should return 269.97", 269.97, exercise.calculateStayTotal(3), 0.001);
		assertEquals("calculateStayTotal(4) should return 359.96", 359.96, exercise.calculateStayTotal(4), 0.001);
		assertEquals("calculateStayTotal(10) should return 899.9", 899.9, exercise.calculateStayTotal(10), 0.001);
	}

	@Test
	public void Exercise04_calculateStayTotal_2() {
		Exercise04_HotelReservation exercise = new Exercise04_HotelReservation();

		assertEquals("calculateStayTotal(1, false) should return 99.99", 99.99, exercise.calculateStayTotal(1, false), 0.001);
		assertEquals("calculateStayTotal(1, true) should return 124.99", 124.99, exercise.calculateStayTotal(1, true), 0.001);
		assertEquals("calculateStayTotal(2, false) should return 199.98", 199.98, exercise.calculateStayTotal(2, false), 0.001);
		assertEquals("calculateStayTotal(2, true) should return 249.98", 249.98, exercise.calculateStayTotal(2, true), 0.001);
		assertEquals("calculateStayTotal(3, false) should return 269.97", 269.97, exercise.calculateStayTotal(3, false), 0.001);
		assertEquals("calculateStayTotal(3, true) should return 344.97", 344.97, exercise.calculateStayTotal(3, true), 0.001);
		assertEquals("calculateStayTotal(4, false) should return 359.96", 359.96, exercise.calculateStayTotal(4, false), 0.001);
		assertEquals("calculateStayTotal(4, true) should return 459.96", 459.96, exercise.calculateStayTotal(4, true), 0.001);
	}

	@Test
	public void Exercise04_calculateStayTotal_3() {
		Exercise04_HotelReservation exercise = new Exercise04_HotelReservation();

		assertEquals("calculateStayTotal(2, false, false) should return 199.98", 199.98, exercise.calculateStayTotal(2, false, false), 0.001);
		assertEquals("calculateStayTotal(2, false, true) should return 219.98", 219.98, exercise.calculateStayTotal(2, false, true), 0.001);
		assertEquals("calculateStayTotal(2, true, false) should return 249.98", 249.98, exercise.calculateStayTotal(2, true, false), 0.001);
		assertEquals("calculateStayTotal(2, true, true) should return 269.98", 269.98, exercise.calculateStayTotal(2, true, true), 0.001);

		assertEquals("calculateStayTotal(3, false, false) should return 269.97", 269.97, exercise.calculateStayTotal(3, false, false), 0.001);
		assertEquals("calculateStayTotal(3, false, true) should return 289.97", 289.97, exercise.calculateStayTotal(3, false, true), 0.001);
		assertEquals("calculateStayTotal(3, true, false) should return 344.97", 344.97, exercise.calculateStayTotal(3, true, false), 0.001);
		assertEquals("calculateStayTotal(3, true, true) should return 364.97", 364.97, exercise.calculateStayTotal(3, true, true), 0.001);
	}

	@Test
	public void Exercise05_acceptPackage_1() {
		Exercise05_AcceptPackage exercise = new Exercise05_AcceptPackage();

		assertTrue("acceptPackage(23) should return true", exercise.acceptPackage(23));
		assertTrue("acceptPackage(40) should return true", exercise.acceptPackage(40));
		assertFalse("acceptPackage(41) should return false", exercise.acceptPackage(41));
		assertFalse("acceptPackage(400) should return false", exercise.acceptPackage(400));
	}

	@Test
	public void Exercise05_acceptPackage_2() {
		Exercise05_AcceptPackage exercise = new Exercise05_AcceptPackage();

		assertTrue("acceptPackage(23, 4, 4, 4) should return true", exercise.acceptPackage(23, 4, 4, 4));
		assertTrue("acceptPackage(23, 19, 19, 19) should return true", exercise.acceptPackage(23, 19, 19, 19));
		assertTrue("acceptPackage(23, 2, 2, 194) should return true", exercise.acceptPackage(23, 2, 2, 194));
		assertTrue("acceptPackage(40, 4, 4, 4) should return true", exercise.acceptPackage(40, 4, 4, 4));
		assertFalse("acceptPackage(23, 20, 20, 20) should return false", exercise.acceptPackage(23, 20, 20, 20));
		assertFalse("acceptPackage(41, 4, 4, 4) should return false", exercise.acceptPackage(41, 4, 4, 4));
		assertFalse("acceptPackage(400, 4, 2, 1) should return false", exercise.acceptPackage(400, 4, 2, 1));
	}

	@Test
	public void Exercise05_acceptPackage_3() {
		Exercise05_AcceptPackage exercise = new Exercise05_AcceptPackage();

		assertTrue("acceptPackage(23, 4, 4, 4, false) should return true", exercise.acceptPackage(23, 4, 4, 4, false));
		assertTrue("acceptPackage(23, 19, 19, 19, false) should return true", exercise.acceptPackage(23, 19, 19, 19, false));
		assertTrue("acceptPackage(23, 2, 2, 194, true) should return true", exercise.acceptPackage(23, 2, 2, 194, true));
		assertTrue("acceptPackage(40, 4, 4, 4, false) should return true", exercise.acceptPackage(40, 4, 4, 4, false));
		assertFalse("acceptPackage(23, 20, 20, 20, true) should return false", exercise.acceptPackage(23, 20, 20, 20, true));
		assertFalse("acceptPackage(23, 20, 20, 20, false) should return false", exercise.acceptPackage(23, 20, 20, 20, false));
		assertFalse("acceptPackage(23, 2, 2, 194, false) should return false", exercise.acceptPackage(23, 2, 2, 194, false));
		assertFalse("acceptPackage(41, 4, 4, 4, true) should return false", exercise.acceptPackage(41, 4, 4, 4, true));
		assertFalse("acceptPackage(400, 4, 2, 1, true) should return false", exercise.acceptPackage(400, 4, 2, 1, true));
	}

	@Test
	public void Exercise06_raceDay_1() {
		Exercise06_RaceDay exercise = new Exercise06_RaceDay();

		assertEquals("determineRaceBlock(17, false) should return 3", 3, exercise.determineRaceBlock(17, false));
		assertEquals("determineRaceBlock(17, true) should return 3", 3, exercise.determineRaceBlock(17, true));
		assertEquals("determineRaceBlock(18, false) should return 2", 2, exercise.determineRaceBlock(18, false));
		assertEquals("determineRaceBlock(18, true) should return 1", 1, exercise.determineRaceBlock(18, true));
		assertEquals("determineRaceBlock(30, false) should return 2", 2, exercise.determineRaceBlock(30, false));
		assertEquals("determineRaceBlock(30, true) should return 1", 1, exercise.determineRaceBlock(30, true));
	}

	@Test
	public void Exercise06_raceDay_2() {
		Exercise06_RaceDay exercise = new Exercise06_RaceDay();

		assertEquals("getBibNumber(17, 500, false) should return 500", 500, exercise.getBibNumber(17, 500, false));
		assertEquals("getBibNumber(17, 500, true) should return 500", 500, exercise.getBibNumber(17, 500, true));
		assertEquals("getBibNumber(18, 600, false) should return 600", 600, exercise.getBibNumber(18, 600, false));
		assertEquals("getBibNumber(18, 600, true) should return 1600", 1600, exercise.getBibNumber(18, 600, true));
		assertEquals("getBibNumber(30, 700, false) should return 700", 700, exercise.getBibNumber(30, 700, false));
		assertEquals("getBibNumber(30, 700, true) should return 1700", 1700, exercise.getBibNumber(30, 700, true));
	}

	@Test
	public void Exercise06_raceDay_3() {
		Exercise06_RaceDay exercise = new Exercise06_RaceDay();

		assertEquals("getConfirmedBibNumber(17, 500, false) should return 500", 500, exercise.getConfirmedBibNumber(17, 500, false));
		assertEquals("getConfirmedBibNumber(17, 500, true) should return 500", 500, exercise.getConfirmedBibNumber(17, 500, true));
		assertEquals("getConfirmedBibNumber(18, 600, false) should return 600", 600, exercise.getConfirmedBibNumber(18, 600, false));
		assertEquals("getConfirmedBibNumber(18, 600, true) should return 1600", 1600, exercise.getConfirmedBibNumber(18, 600, true));
		assertEquals("getConfirmedBibNumber(30, 700, false) should return 700", 700, exercise.getConfirmedBibNumber(30, 700, false));
		assertEquals("getConfirmedBibNumber(30, 700, true) should return 1700", 1700, exercise.getConfirmedBibNumber(30, 700, true));
		assertEquals("getConfirmedBibNumber(30, 1001, false) should return -1", -1, exercise.getConfirmedBibNumber(30, 1001, false));
		assertEquals("getConfirmedBibNumber(30, 1001, true) should return 2001", 2001, exercise.getConfirmedBibNumber(30, 1001, true));
	}
	
	@Test
	public void Exercise07_isStoreOpen_1() {
		Exercise07_StoreHours exercise = new Exercise07_StoreHours();
		assertTrue("isStoreOpen(8) should return true", exercise.isStoreOpen(8));
		assertTrue("isStoreOpen(10) should return true", exercise.isStoreOpen(10));
		assertTrue("isStoreOpen(16) should return true", exercise.isStoreOpen(16));
		assertFalse("isStoreOpen(17) should return false", exercise.isStoreOpen(17));
		assertFalse("isStoreOpen(19) should return false", exercise.isStoreOpen(19));
		assertFalse("isStoreOpen(23) should return false", exercise.isStoreOpen(23));
		assertFalse("isStoreOpen(0) should return false", exercise.isStoreOpen(0));
		assertFalse("isStoreOpen(1) should return false", exercise.isStoreOpen(1));
		assertFalse("isStoreOpen(7) should return false", exercise.isStoreOpen(7));
	}

	@Test
	public void Exercise07_isStoreOpen_2() {
		Exercise07_StoreHours exercise = new Exercise07_StoreHours();
		assertTrue("isStoreOpen(8, 'M') should return true", exercise.isStoreOpen(8, 'M'));
		assertTrue("isStoreOpen(8, 'W') should return true", exercise.isStoreOpen(8, 'W'));
		assertTrue("isStoreOpen(8, 'F') should return true", exercise.isStoreOpen(8, 'F'));
		assertFalse("isStoreOpen(8, 'S') should return false", exercise.isStoreOpen(8, 'S'));

		assertTrue("isStoreOpen(13, 'M') should return true", exercise.isStoreOpen(13, 'M'));
		assertTrue("isStoreOpen(13, 'W') should return true", exercise.isStoreOpen(13, 'W'));
		assertTrue("isStoreOpen(13, 'F') should return true", exercise.isStoreOpen(13, 'F'));
		assertFalse("isStoreOpen(13, 'S') should return false", exercise.isStoreOpen(13, 'S'));

		assertTrue("isStoreOpen(16, 'M') should return true", exercise.isStoreOpen(16, 'M'));
		assertTrue("isStoreOpen(16, 'W') should return true", exercise.isStoreOpen(16, 'W'));
		assertTrue("isStoreOpen(16, 'F') should return true", exercise.isStoreOpen(16, 'F'));
		assertFalse("isStoreOpen(16, 'S') should return false", exercise.isStoreOpen(16, 'S'));

		assertFalse("isStoreOpen(17, 'M') should return false", exercise.isStoreOpen(17, 'M'));
		assertFalse("isStoreOpen(17, 'W') should return false", exercise.isStoreOpen(17, 'W'));
		assertFalse("isStoreOpen(17, 'F') should return false", exercise.isStoreOpen(17, 'F'));
		assertFalse("isStoreOpen(17, 'S') should return false", exercise.isStoreOpen(17, 'S'));

		assertFalse("isStoreOpen(19, 'M') should return false", exercise.isStoreOpen(19, 'M'));
		assertFalse("isStoreOpen(19, 'W') should return false", exercise.isStoreOpen(19, 'W'));
		assertFalse("isStoreOpen(19, 'F') should return false", exercise.isStoreOpen(19, 'F'));
		assertFalse("isStoreOpen(19, 'S') should return false", exercise.isStoreOpen(19, 'S'));

		assertFalse("isStoreOpen(7, 'M') should return false", exercise.isStoreOpen(7, 'M'));
		assertFalse("isStoreOpen(7, 'W') should return false", exercise.isStoreOpen(7, 'W'));
		assertFalse("isStoreOpen(7, 'F') should return false", exercise.isStoreOpen(7, 'F'));
		assertFalse("isStoreOpen(7, 'S') should return false", exercise.isStoreOpen(7, 'S'));
	}

	@Test
	public void Exercise07_isStoreOpen_3() {
		Exercise07_StoreHours exercise = new Exercise07_StoreHours();
		assertFalse("isStoreOpen(7, 'M', false) should return false", exercise.isStoreOpen(7, 'M', false));
		assertFalse("isStoreOpen(7, 'M', true) should return false", exercise.isStoreOpen(7, 'M', true));
		assertFalse("isStoreOpen(7, 'W', false) should return false", exercise.isStoreOpen(7, 'W', false));
		assertFalse("isStoreOpen(7, 'W', true) should return false", exercise.isStoreOpen(7, 'W', true));
		assertFalse("isStoreOpen(7, 'F', false) should return false", exercise.isStoreOpen(7, 'F', false));
		assertFalse("isStoreOpen(7, 'F', true) should return false", exercise.isStoreOpen(7, 'F', true));
		assertFalse("isStoreOpen(7, 'S', false) should return false", exercise.isStoreOpen(7, 'S', false));
		assertFalse("isStoreOpen(7, 'S', true) should return false", exercise.isStoreOpen(7, 'S', true));

		assertTrue("isStoreOpen(8, 'M', false) should return true", exercise.isStoreOpen(8, 'M', false));
		assertTrue("isStoreOpen(8, 'M', true) should return true", exercise.isStoreOpen(8, 'M', true));
		assertTrue("isStoreOpen(8, 'W', false) should return true", exercise.isStoreOpen(8, 'W', false));
		assertTrue("isStoreOpen(8, 'W', true) should return true", exercise.isStoreOpen(8, 'W', true));
		assertTrue("isStoreOpen(8, 'F', false) should return true", exercise.isStoreOpen(8, 'F', false));
		assertTrue("isStoreOpen(8, 'F', true) should return true", exercise.isStoreOpen(8, 'F', true));
		assertFalse("isStoreOpen(8, 'S', false) should return false", exercise.isStoreOpen(8, 'S', false));
		assertFalse("isStoreOpen(8, 'S', true) should return false", exercise.isStoreOpen(8, 'S', true));

		assertFalse("isStoreOpen(9, 'S', false) should return false", exercise.isStoreOpen(9, 'S', false));
		assertTrue("isStoreOpen(9, 'S', true) should return true", exercise.isStoreOpen(9, 'S', true));

		assertFalse("isStoreOpen(15, 'S', false) should return false", exercise.isStoreOpen(15, 'S', false));
		assertFalse("isStoreOpen(15, 'S', true) should return false", exercise.isStoreOpen(15, 'S', true));

		assertFalse("isStoreOpen(16, 'S', false) should return false", exercise.isStoreOpen(16, 'S', false));
		assertFalse("isStoreOpen(16, 'S', true) should return false", exercise.isStoreOpen(16, 'S', true));

		assertFalse("isStoreOpen(17, 'M', false) should return false", exercise.isStoreOpen(17, 'M', false));
		assertFalse("isStoreOpen(17, 'M', true) should return false", exercise.isStoreOpen(17, 'M', true));
		assertFalse("isStoreOpen(17, 'W', false) should return false", exercise.isStoreOpen(17, 'W', false));
		assertTrue("isStoreOpen(17, 'W', true) should return true", exercise.isStoreOpen(17, 'W', true));
		assertFalse("isStoreOpen(17, 'F', false) should return false", exercise.isStoreOpen(17, 'F', false));
		assertFalse("isStoreOpen(17, 'F', true) should return false", exercise.isStoreOpen(17, 'F', true));
		assertFalse("isStoreOpen(17, 'S', false) should return false", exercise.isStoreOpen(17, 'S', false));
		assertFalse("isStoreOpen(17, 'S', true) should return false", exercise.isStoreOpen(17, 'S', true));

		assertFalse("isStoreOpen(19, 'W', false) should return false", exercise.isStoreOpen(19, 'W', false));
		assertTrue("isStoreOpen(19, 'W', true) should return true", exercise.isStoreOpen(19, 'W', true));
		assertFalse("isStoreOpen(20, 'W', false) should return false", exercise.isStoreOpen(20, 'W', false));
		assertFalse("isStoreOpen(20, 'W', true) should return false", exercise.isStoreOpen(20, 'W', true));
	}
}
