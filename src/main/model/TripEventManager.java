package model;

import java.util.ArrayList;
import java.util.Collections;

public class TripEventManager {

    private ArrayList<TripEvent> events;

    // EFFECTS: creates a new trip event manager object
    public TripEventManager() {
        events = new ArrayList<>();
    }

    // EFFECTS: returns itinerary's events
    public ArrayList<TripEvent> getEvents() {
        return events;
    }

    // EFFECTS: returns event at given time; if no event found, returns null
    public TripEvent getEvent(String time) {
        for (TripEvent event: events) {
            if (event.getTimeString24().equals(time)) {
                return event;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: adds new event to current planned events
    public void addNewEvent(String activity, String time, String address) {
        TripEvent newEvent = new TripEvent(activity, time, address);
        events.add(newEvent);
    }

    // MODIFIES: this
    // EFFECTS: removes event at given time
    public void removeEvent(String time) {
        TripEvent te = null;
        for (TripEvent event : events) {
            if (event.getTimeString24().equals(time)) {
                te = event;
            }
        }
        if (te != null) {
            events.remove(te);
        }
    }

    // REQUIRES: element to be in events
    // MODIFIES: this
    // EFFECTS: sorts events in chronological order
    public void sortItinerary() {
        Collections.sort(events);
    }


}
