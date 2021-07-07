package test;

import model.DailyItinerary;
import model.Itineraries;
import model.TripDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDailyItinerary {

    DailyItinerary di;

    @BeforeEach
    public void setup() {
        di = new DailyItinerary("my new trip!", "02/12/2019");

    }

    @Test
    public void testConstructorTwoString() {
        DailyItinerary dailyItinerary = new DailyItinerary("a","02/14/2017");
        assertTrue(dailyItinerary.getDateString().equals("02/14/2017"));
        assertTrue(dailyItinerary.getTripName().equals("a"));
        assertTrue(dailyItinerary.getEventManager() != null);
    }

    @Test
    public void testConstructorStringTripDate() {
        TripDate tripDate = new TripDate("03/15/2018");
        DailyItinerary dailyItinerary = new DailyItinerary("b", tripDate);
        assertTrue(dailyItinerary.getDateString().equals("03/15/2018"));
        assertTrue(dailyItinerary.getTripName().equals("b"));
        assertTrue(dailyItinerary.getEventManager() != null);
    }


//    @Test
//    public void testSetOverviewItinerary() {
//        OverviewItinerary overviewItinerary =
//                new OverviewItinerary("abc", "07/12/2019", "07/13/2019");
//        di.setOverviewItinerary(overviewItinerary);
//
//        assertTrue(di.getOverviewItinerary().getTripName().equals("abc"));
//    }
//
//    @Test
//    public void testGetOverviewItinerary() {
//        OverviewItinerary overviewItinerary =
//                new OverviewItinerary("asdf", "08/02/2019", "08/05/2019");
//        di.setOverviewItinerary(overviewItinerary);
//
//        assertTrue(di.getOverviewItinerary() != null);
//    }

    @Test
    public void testGetEventManager() {
        assertTrue(di.getEventManager() != null);
        di.getEventManager().addNewEvent("asdf","07:00", "Vancouver, BC");
        assertTrue(di.getEventManager().getEvents().size() == 1);
    }

    @Test
    public void testGetDateString() {
        assertTrue(di.getDateString().equals("02/12/2019"));
    }

    @Test
    public void testGetDate() {
        TripDate tripDate = new TripDate("02/12/2019");
        assertTrue(di.getDate() != null);
        assertTrue(di.getDate().equals(tripDate));
    }

    @Test
    public void testAddTripEventSuccessful() {
        di.addTripEvent("aweiof", "04:00", "oawije");
        assertTrue(di.getEventManager().getEvents().size() == 1);
    }

    @Test
    public void testAddTripUnsuccessful() {
        di.addTripEvent("a", "05:00", "a");
        assertTrue(di.getEventManager().getEvents().size() == 1);

        di.addTripEvent("a", "05:00", "a");
        assertTrue(di.getEventManager().getEvents().size() == 1);
    }

    @Test
    public void testEqualsAndHashCodeSameObject() {
        assertTrue(di.equals(di));
        assertTrue(di.hashCode() == di.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeDifferentObject() {
        DailyItinerary di1 = new DailyItinerary("awef", "02/12/2019");
        di1.addTripEvent("Asdf","08:00", "0aiewjf");
        assertTrue(di.equals(di1));
        assertTrue(di1.hashCode() == di.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeNull() {
        assertFalse(di.equals(null));
    }

    @Test
    public void testEqualsAndHashCodeDifferentClasses() {
        Itineraries i = new Itineraries();
        assertFalse(di.equals(i));
    }

    @Test
    public void testEqualsAndHashCodeNotEqual() {
        DailyItinerary di1 = new DailyItinerary("my new trip!", "03/14/2019");
        assertFalse(di.equals(di1));
        assertFalse(di.hashCode() == di1.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeDifferentTripNames() {
        DailyItinerary di1 = new DailyItinerary("aowiej", "02/12/2019");
        assertTrue(di.equals(di1));
        assertTrue(di.hashCode() == di1.hashCode());
    }

    @Test
    public void testToString() {
        String test = di.toString();
        assertTrue(test.equals("my new trip!"));
    }
}
