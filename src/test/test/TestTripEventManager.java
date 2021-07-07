package test;

import model.TripEventManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestTripEventManager {

    TripEventManager tem;

    @BeforeEach
    public void setup() {
        tem = new TripEventManager();
    }

    @Test
    public void testConstructor() {
        assertTrue(tem.getEvents().size() == 0);
    }

    @Test
    public void testGetEventsEmptyList() {
        assertTrue(tem.getEvents().isEmpty());
    }

    @Test
    public void testGetEventsWithElementsInside() {
        tem.addNewEvent("go shopping", "13:00", "UBC, Vancouver, BC");

        assertTrue(tem.getEvents().get(0).getTimeString12().equals("01:00 PM"));
        assertFalse(tem.getEvents().isEmpty());
    }

    @Test
    public void testGetEventSuccessful() {
        tem.addNewEvent("awefawef", "15:00", "UBC");
        tem.addNewEvent("aowieo", "09:00", "Aawefj");

        assertTrue(tem.getEvent("09:00").getActivity().equals("aowieo"));
    }

    @Test
    public void testGetEventUnsuccessful() {
        tem.addNewEvent("idk", "07:35", "asdf");
        tem.addNewEvent("k", "08:00", "as");

        assertTrue(tem.getEvent("10:00") == null);
    }

    @Test
    public void testGetEventEmptyList() {
        assertTrue(tem.getEvent("08:00") == null);
    }

    @Test
    public void testRemoveElementSuccessful() {
        tem.addNewEvent("aoweij", "09:00", "idk");
        tem.addNewEvent("aw", "05:00", "k");

        tem.removeEvent("05:00");

        assertTrue(tem.getEvents().size() == 1);
    }

    @Test
    public void testRemoveElementEmptyList() {
        tem.removeEvent("08:00");

        assertTrue(tem.getEvents().size() == 0);
    }

    @Test
    public void testRemoveElementUnsuccessful() {
        tem.addNewEvent("a", "08:35", "ka");
        tem.addNewEvent("b", "12:45", "j");

        tem.removeEvent("09:49");

        assertTrue(tem.getEvents().size() == 2);
    }

    @Test
    public void testAddNewEventOneElement() {
        tem.addNewEvent("go eat", "18:00", "UBC, Vancouver, BC");

        assertTrue(tem.getEvents().get(0).getTimeString12().equals("06:00 PM"));
    }

    @Test
    public void testAddNewEventTwoElements() {
        tem.addNewEvent("check in at hotel", "10:00", "Vancouver, BC");
        tem.addNewEvent("meet up with friends", "15:00", "Vancouver, BC");

        assertTrue(tem.getEvents().get(0).getActivity().equals("check in at hotel"));
        assertTrue(tem.getEvents().get(1).getTimeString12().equals("03:00 PM"));
    }

    @Test
    public void testSortItinerary() {
        tem.addNewEvent("check in at hotel","08:00", "Vancouver, BC");
        tem.addNewEvent("go shopping","12:00", "Vancouver, BC");
        tem.addNewEvent("arrive from airport","06:00", "Vancouver, BC");

        tem.sortItinerary();

        assertTrue(tem.getEvents().get(0).getTimeString12().equals("06:00 AM"));
        assertTrue(tem.getEvents().get(2).getTimeString12().equals("12:00 PM"));
    }
}
