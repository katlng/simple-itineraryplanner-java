package model;

import java.util.Objects;

public class DailyItinerary extends Itinerary {

    private TripEventManager eventManager;
    private TripDate date;

    // EFFECTS: creates a DailyItinerary object, instantiating the fields,
    //          uses a string to create a new Date
    public DailyItinerary(String tripName, String date) {
        super(tripName);
        eventManager = new TripEventManager();
        this.date = createDate(date);
    }

    // EFFECTS: creates a daily itinerary object using a string and tripDate
    public DailyItinerary(String tripName, TripDate date) {
        super(tripName);
        eventManager = new TripEventManager();
        this.date = date;
    }

    // EFFECTS: returns itinerary's event manager
    public TripEventManager getEventManager() {
        return eventManager;
    }

    // EFFECTS: returns date in String form
    @Override
    public String getDateString() {
        return date.getDateString();
    }

    // EFFECTS: returns date
    public TripDate getDate() {
        return date;
    }

    // MODIFIES: this
    // EFFECTS: adds trip event to the list of events if not already there, if there, do nothing
    public void addTripEvent(String activity, String time, String address) {
        TripEvent tripEvent = new TripEvent(activity, time, address);

        if (!eventManager.getEvents().contains(tripEvent)) {
            eventManager.getEvents().add(tripEvent);
        }
    }

    // EFFECTS: checks if two daily itineraries are equal; they're equal if they have the same date
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DailyItinerary that = (DailyItinerary) o;
        return Objects.equals(getDate(), that.getDate());
    }

    // EFFECTS: generates a hashcode for a daily itinerary object based off its date
    @Override
    public int hashCode() {

        return Objects.hash(getDate());
    }

    // EFFECTS: when printing an instance of an itinerary
    //          the itinerary's trip name gets printed
    @Override
    public String toString() {
        return tripName;
    }
}
