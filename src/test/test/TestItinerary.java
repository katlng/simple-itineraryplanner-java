package test;

import model.DailyItinerary;
import model.Itinerary;
import model.TripDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class TestItinerary {

    Itinerary testI;

    @BeforeEach
    public void setup() {
        testI = new DailyItinerary("my new trip!", "02/12/2019");
    }
    @Test
    public void testSetTripName() {
        testI.setTripName("Thailand");
        assertTrue(testI.getTripName().equals("Thailand"));
    }

    @Test
    public void testSetLocationCountry() {
        testI.setLocationCountry("Canada");

        assertTrue(testI.getLocationCountry().equals("Canada"));
        testI.setLocationCountry("America");
        testI.setLocationCountry("Japan");

        assertTrue(testI.getLocationCountry().equals("Japan"));
    }

    @Test
    public void testGetLocationCountry() {
        testI.setLocationCountry("Canada");

        assertTrue(testI.getLocationCountry().equals("Canada"));

        testI.setLocationCountry("South Korea");
        testI.setLocationCountry("North Korea");

        assertTrue(testI.getLocationCountry().equals("North Korea"));
    }

    @Test
    public void testGetTripName() {
        assertTrue(testI.getTripName().equals("my new trip!"));
    }

    @Test
    public void testCreateDateSuccessful() {
        TripDate td = testI.createDate("07/29/2019");
        assertTrue(td != null);
    }

    @Test
    public void testCreateDateUnsuccessful() {
        TripDate td = testI.createDate("0819834");
        assertTrue(td.getDate() == null);
    }

}
