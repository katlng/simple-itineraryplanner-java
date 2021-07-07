package test;

import model.Itineraries;
import model.TripDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestTripDate {

    TripDate td;

    @BeforeEach
    public void setup() {
        td = new TripDate("10/23/2019");
    }

    @Test
    public void testConstructor() {
        TripDate tripDate;

        tripDate = new TripDate("08/23/2017");
        assertTrue(tripDate.getDateString().equals("08/23/2017"));
    }

    @Test
    public void testConstructorUnsuccessful() {
        TripDate tripDate = new TripDate("129847192");
        assertTrue(tripDate.getDate() == null);
    }

    @Test
    public void testGetDate() {
        TripDate td2 = new TripDate("10/23/2019");
        Date td3 = td.getDate();

        assertTrue(td.getDate().equals(td2.getDate()));
        assertTrue(td3 != null);
    }

    @Test
    public void testGetDateString() {
        assertTrue(td.getDateString().equals("10/23/2019"));
    }

    @Test
    public void testGetNextDate() {
        TripDate td3 = new TripDate("10/24/2019");
        assertTrue(td.getNextDate().getDateString().equals("10/24/2019"));
        assertTrue(td.getNextDate().getDate().equals(td3.getDate()));
    }

    @Test
    public void testEqualsAndHashcode() {
        TripDate td3 = new TripDate("10/23/2019");

        assertTrue(td.equals(td3));
        assertTrue(td.hashCode() == td3.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeSameObject() {

        assertTrue(td.equals(td));
        assertTrue(td.hashCode() == td.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeNullObject() {
        TripDate td1 = null;

        assertFalse(td.equals(td1));
    }

    @Test
    public void testEqualsAndHashCodeDifferentClasses() {
        Itineraries i = new Itineraries();
        assertFalse(td.equals(i));
    }

    @Test
    public void testEqualsAndHashCodeNotEqual() {
        TripDate tripDate = new TripDate("04/12/2019");
        assertFalse(td.equals(tripDate));
        assertFalse(td.hashCode() == tripDate.hashCode());
    }

    @Test
    public void testCompareTo() {
        TripDate td1 = new TripDate("10/24/2019");

        assertTrue(td.compareTo(td1) < 0);
    }

    @Test
    public void testCompareToEarlierDate() {
        TripDate td1 = new TripDate("10/22/2019");

        assertTrue(td.compareTo(td1) > 0);
    }


}
