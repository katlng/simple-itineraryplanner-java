package test;

import model.DailyItinerary;
import model.OverviewItinerary;
import model.TripDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static junit.framework.TestCase.assertTrue;

public class TestOverviewItinerary {

    OverviewItinerary oi;

    @BeforeEach
    public void setup() {
        oi = new OverviewItinerary("bali","06/12/2019","06/20/2019");
    }

    @Test
    public void testConstructorThreeStrings() {
        OverviewItinerary oi1 = new OverviewItinerary("a", "08/12/2019", "08/14/2019");
        assertTrue(oi.getTripDates().size() == 9);
        assertTrue(oi.getTripName().equals("bali"));
        assertTrue(oi1.getTripName().equals("a"));
        assertTrue(oi1.getTripDates().size() == 3);
    }

    @Test
    public void testConstructorStringTripDates() {
        TripDate date = new TripDate("04/12/2019");
        TripDate date1 = new TripDate("04/15/2019");
        OverviewItinerary oi1 = new OverviewItinerary("a", date, date1);
        assertTrue(oi1.getTripDates().size() == 4);
        assertTrue(oi1.getTripName().equals("a"));
    }

    @Test
    public void testGetTripDates() {
        assertTrue(oi.getTripDates().size() == 9);
        assertTrue(oi.getTripDates().contains(new TripDate("06/12/2019")));
    }

    @Test
    public void testGetDailyItineraries() {
        assertTrue(oi.getDailyItineraries() != null);
        assertTrue(oi.getDailyItineraries().size() == 9);
    }

    @Test
    public void testGetDailyItineraryNonNull() {
        DailyItinerary di = new DailyItinerary("bali","06/13/2019");
        oi.addDailyItinerary(di);
        assertTrue(oi.getDailyItinerary("06/13/2019") != null);
    }

    @Test
    public void testGetDailyItineraryNull() {
        assertTrue(oi.getDailyItinerary("07/14/2019") == null);
    }

    @Test
    public void testGetDailyItineraryEmptyList() {
        oi.getDailyItineraries().clear();
        assertTrue(oi.getDailyItinerary("06/12/2019") == null);
    }

    @Test
    public void testGetDateSting() {
        assertTrue(oi.getDateString().equals("06/12/2019 - 06/20/2019"));
    }

//    @Test
//    public void testAddDailyItinerarySuccessful() {
//        DailyItinerary di1 = new DailyItinerary("asd","07/12/2019");
//        oi.addDailyItinerary(di1);
//        assertTrue(oi.getDailyItineraries().size() == 10);
//
//        assertTrue(di1.getOverviewItinerary() != null);
//    }

    @Test
    public void testAddDailyItineraryAlreadyContained() {
        DailyItinerary d1 = new DailyItinerary("bali", "07/12/2019");
        DailyItinerary d2 = new DailyItinerary("bali","07/12/2019");
        oi.addDailyItinerary(d1);
        oi.addDailyItinerary(d2);

        assertTrue(oi.getDailyItineraries().size() == 10);
    }

    @Test
    public void testAddDailyItineraryNullDate() {
        DailyItinerary d1 = new DailyItinerary("aas", "23423482");
        oi.addDailyItinerary(d1);

        assertTrue(oi.getDailyItineraries().size() == 9);
    }

//    @Test
//    public void testAddDailyItineraryDateAlreadyContained() {
//        DailyItinerary d1 = new DailyItinerary("awef", "06/12/2019");
//        oi.addDailyItinerary(d1);
//
//        assertTrue(oi.getDailyItineraries().size() == 9);
//        assertTrue(d1.getOverviewItinerary().equals(oi));
//    }

    @Test
    public void testAddTripDateKeySuccessful() throws ParseException {
        TripDate td = new TripDate("07/13/2019");
        oi.addTripDateKey(td);
        assertTrue(oi.getDailyItineraries().size() == 10);
    }

    @Test
    public void testAddTripDateKeyUnsuccessful() throws ParseException {
        TripDate td = new TripDate("06/12/2019");
        oi.addTripDateKey(td);
        assertTrue(oi.getDailyItineraries().size() == 9);
    }

    @Test
    public void testCreateDatesBetween() {
        oi.createDatesBetween();
        assertTrue(oi.getTripDates().size() == 9);
    }

//    @Test
//    public void testSortTripDatesAlreadyInOrder() {
//        oi.sortTripDates();
//        assertTrue(oi.getTripDates().get(0).getDateString().equals("06/12/2019"));
//    }
//
//    @Test
//    public void testSortTripDatesNotSorted() throws ParseException {
//        TripDate td1 = new TripDate("06/13/2019");
//        TripDate td2 = new TripDate("06/15/2019");
//
//        oi.getTripDates().add(td1);
//        oi.getTripDates().add(td2);
//
//        oi.sortTripDates();
//        assertTrue(oi.getTripDates().get(1).getDateString().equals("06/13/2019"));
//    }

    @Test
    public void testCreateDailyItineraryUsingDate() throws ParseException {
        TripDate td = new TripDate("08/12/2018");
        DailyItinerary dailyItinerary = oi.createDailyItineraryUsingDate(td);
        assertTrue(dailyItinerary.getDateString().equals("08/12/2018"));
    }

    @Test
    public void testCreateDailyItinerariesBetween() {
        oi.createDatesBetween();
        oi.createDailyItinerariesBetween();
        for (TripDate td : oi.getDailyItineraries().keySet()) {
            assertTrue(oi.getDailyItineraries().get(td) != null);
        }
    }

    @Test
    public void testToString() {
        String test = oi.toString();
        assertTrue(test.equals("bali"));
    }

}
