package test;

import model.Itineraries;
import model.TripEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTripEvent {

    TripEvent te;

    @BeforeEach
    public void setup() {
        te = new TripEvent("go to hotel", "08:00","Vancouver, BC");
    }

    @Test
    public void testConstructor() {
        TripEvent tripEvent = new TripEvent("shopping","08:00","Vancouver, BC");

        assertTrue(tripEvent.getAddress().equals("Vancouver, BC"));
        assertTrue(tripEvent.getActivity().equals("shopping"));
        assertTrue(tripEvent.getTimeString12().equals("08:00 AM"));
    }

    @Test
    public void testConstructorUnsuccessfulTime() {
        TripEvent tripEvent = new TripEvent("go to hotel", "1098234", "Canada");

        assertTrue(tripEvent.getTime() == null);
    }

    @Test
    public void testSetActivity() {
        te.setActivity("meet up with relative");
        assertTrue(te.getActivity().equals("meet up with relative"));
    }

    @Test
    public void testSetTime() {
        assertTrue(te.getTimeString12().equals("08:00 AM"));

        te.setTime("12:00");
        assertTrue(te.getTimeString12().equals("12:00 PM"));
    }

    @Test
    public void testSetTimeUnsuccessful() {
        te.setTime("129831294");

        assertTrue(te.getTimeString12().equals("08:00 AM"));
    }

    @Test
    public void testSetAddress() {
        String s1 = te.getAddress();

        te.setAddress("1230, Make-believe address");

        String s2 = te.getAddress();

        assertFalse(s1.equals(s2));
        assertTrue(te.getAddress().equals("1230, Make-believe address"));
    }

    @Test
    public void testGetActivity() {
        assertTrue(te.getActivity().equals("go to hotel"));

        te.setActivity("go sightseeing");
        assertTrue(te.getActivity().equals("go sightseeing"));
    }

    @Test
    public void testGetTime() {
        TripEvent te2 = new TripEvent("aowiej","08:00","doesn't matter");

        assertTrue(te.getTime().equals(te2.getTime()));
    }

    @Test
    public void testGetTimeString12() {
        assertTrue(te.getTimeString12().equals("08:00 AM"));

        te.setTime("09:00");
        assertTrue(te.getTimeString12().equals("09:00 AM"));
    }

    @Test
    public void testGetTimeString24() {
        assertTrue(te.getTimeString24().equals("08:00"));

        te.setTime("15:00");
        assertTrue(te.getTimeString24().equals("15:00"));
    }

    @Test
    public void testGetAddress() {
        assertTrue(te.getAddress().equals("Vancouver, BC"));

        te.setAddress("i don't know");
        assertTrue(te.getAddress().equals("i don't know"));
    }

    @Test
    public void testCompareTo() {
        TripEvent te1 = new TripEvent("asdf","09:00","make-believe");
        assertTrue(te.compareTo(te1) < 0);
    }

    @Test
    public void testCompareToEarlierEvent() {
        TripEvent te1 = new TripEvent("aoijew", "05:00", "awjf");
        assertTrue(te.compareTo(te1) > 0);
    }

    @Test
    public void testEqualsAndHashSameObject() {

        assertTrue(te.equals(te));
        assertTrue(te.hashCode() == te.hashCode());
    }

    @Test
    public void testEqualsDifferentTime() {
        TripEvent te1 = new TripEvent("go to hotel", "09:00", "Vancouver, BC");

        assertTrue(te.equals(te1));
        assertTrue(te.hashCode() == te1.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeNullObject() {
        TripEvent te1 = null;

        assertFalse(te.equals(te1));
    }

    @Test
    public void testEqualsAndHashCodeDifferentClasses() {
        Itineraries i = new Itineraries();
        assertFalse(te.equals(i));
    }

    @Test
    public void testEqualsNotEqual() {
        TripEvent te1 = new TripEvent("go toaoweifj", "05:00", "foi");
        assertFalse(te.equals(te1));
        assertFalse(te.hashCode() == te1.hashCode());
    }

    @Test
    public void testNotEqualHasOneFieldInCommon() {
        TripEvent te1 = new TripEvent("go to hotel", "08:00", "awoi");
        assertFalse(te.equals(te1));
        assertFalse(te.hashCode() == te1.hashCode());
    }

    @Test
    public void testEquals() {
        TripEvent te1 = new TripEvent("go to hotel", "08:00", "Vancouver, BC");
        assertTrue(te.equals(te1));
        assertTrue(te.hashCode() == te1.hashCode());
    }

    @Test
    public void testNotEqualHasAddressInCommon() {
        TripEvent te1 = new TripEvent("aoji", "08:00", "Vancouver, BC");
        assertFalse(te.equals(te1));
        assertFalse(te.hashCode() == te1.hashCode());
    }



}
