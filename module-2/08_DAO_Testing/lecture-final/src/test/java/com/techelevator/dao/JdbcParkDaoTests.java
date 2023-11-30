package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private static final Park PARK_1 =
            new Park(1, "Park 1", LocalDate.parse("1800-01-02"), 100, true);
    private static final Park PARK_2 =
            new Park(2, "Park 2", LocalDate.parse("1900-12-31"), 200, false);
    private static final Park PARK_3 =
            new Park(3, "Park 3", LocalDate.parse("2000-06-15"), 300, false);

    private JdbcParkDao sut;

    @Before
    public void setup() {
        sut = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParkById_with_valid_id_returns_correct_park() {
        // arrange

        // act
        Park park1 = sut.getParkById(1);

        // assert
        assertParksMatch(PARK_1, park1);


        Park park2 = sut.getParkById(2);
        assertParksMatch(PARK_2, park2);
    }

    @Test
    public void getParkById_with_invalid_id_returns_null_park() {
        Park park = sut.getParkById(99);
        Assert.assertNull(park);
    }

    @Test
    public void getParksByState_with_valid_state_returns_correct_parks() {
        List<Park> parksInAA = sut.getParksByState("AA");
        Assert.assertEquals(2, parksInAA.size());
        assertParksMatch(PARK_1, parksInAA.get(0));
        assertParksMatch(PARK_3, parksInAA.get(1));

        List<Park> parksInBB = sut.getParksByState("BB");
        Assert.assertEquals(1, parksInBB.size());
        assertParksMatch(PARK_2, parksInBB.get(0));
    }

    @Test
    public void getParksByState_with_invalid_state_returns_empty_list() {
        List<Park> parksInXX = sut.getParksByState("XX");
        Assert.assertEquals(0, parksInXX.size());
    }

    @Test
    public void createPark_creates_park() {
        Park testPark = new Park(0, "Disney", LocalDate.of(1930, 1, 5), 10.5, false);

        Park createdPark = sut.createPark(testPark);
        Assert.assertNotNull(createdPark);

        int newParkId = createdPark.getParkId();
        Assert.assertTrue(newParkId > 0);

        Park retrievedPark = sut.getParkById(newParkId);
//        assertParksMatch(createdPark, retrievedPark);

        testPark.setParkId(newParkId);
        assertParksMatch(testPark, retrievedPark);
    }

    @Test
    public void updatePark_updates_park() {

        Park park2 = sut.getParkById(2);

        park2.setParkName( park2.getParkName() + "blah" );
        park2.setHasCamping(!park2.getHasCamping());
        park2.setArea(park2.getArea() + 1.5);
        park2.setDateEstablished(park2.getDateEstablished().plusYears(1));

        Park updatedPark = sut.updatePark(park2);
        Assert.assertNotNull(updatedPark);

        assertParksMatch(park2, updatedPark);

        Park retrievedPark = sut.getParkById(park2.getParkId());

        assertParksMatch(updatedPark, retrievedPark);

    }

    @Test
    public void deleteParkById_deletes_park() {
        int rowsAffected = sut.deleteParkById(1);
        Assert.assertEquals(1, rowsAffected);

        Park park1 = sut.getParkById(1);
        Assert.assertNull(park1);
    }

    @Test
    public void linkParkState_adds_park_to_list_of_parks_in_state() {

        sut.linkParkState(1, "BB");

        List<Park> parksInBB = sut.getParksByState("BB");
        Assert.assertEquals(2, parksInBB.size());
        assertParksMatch(PARK_1, parksInBB.get(0));
        assertParksMatch(PARK_2, parksInBB.get(1));

    }

    @Test
    public void unlinkParkState_removes_park_from_list_of_parks_in_state() {
        sut.unlinkParkState(2, "BB");

        List<Park> parksInBB = sut.getParksByState("BB");
        Assert.assertEquals(0, parksInBB.size());
    }

    private void assertParksMatch(Park expected, Park actual) {
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getParkName(), actual.getParkName());
        Assert.assertEquals(expected.getDateEstablished(), actual.getDateEstablished());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
        Assert.assertEquals(expected.getHasCamping(), actual.getHasCamping());
    }

}
