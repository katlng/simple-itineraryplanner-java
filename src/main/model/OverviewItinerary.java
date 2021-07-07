package model;

import java.util.Set;
import java.util.TreeMap;

public class OverviewItinerary extends Itinerary {

    private TripDate startDate;
    private TripDate endDate;
    private TreeMap<TripDate, DailyItinerary> dailyItineraries;

    // EFFECTS: sets up daily itineraries and dates of trip, with start date and end date
    public OverviewItinerary(String tripName, String startDate, String endDate) {
        super(tripName);
        dailyItineraries = new TreeMap<>();
        this.startDate = createDate(startDate);
        this.endDate = createDate(endDate);
        addTripDateKey(this.startDate);
        addTripDateKey(this.endDate);
        createDatesBetween();
        createDailyItinerariesBetween();
    }

    // EFFECTS: creates new overview itinerary using string and two trip dates
    public OverviewItinerary(String tripName, TripDate startDate, TripDate endDate) {
        super(tripName);
        dailyItineraries = new TreeMap<>();
        this.startDate = startDate;
        this.endDate = endDate;
        addTripDateKey(this.startDate);
        addTripDateKey(this.endDate);
        createDatesBetween();
        createDailyItinerariesBetween();
    }

    // EFFECTS: returns map of daily itineraries with date keys
    public TreeMap<TripDate, DailyItinerary> getDailyItineraries() {
        return dailyItineraries;
    }

    // EFFECTS: returns the trip's dates
    public Set<TripDate> getTripDates() {
        return dailyItineraries.keySet();
    }

    // EFFECTS: returns daily itinerary associated with given date, if daily itinerary doesn't exist
    //          returns null
    public DailyItinerary getDailyItinerary(String key) {
        for (TripDate tripDate : dailyItineraries.keySet()) {
            if (tripDate.getDateString().equals(key)) {
                return dailyItineraries.get(tripDate);
            }
        }
        return null;
    }

    // EFFECTS: returns the trip's duration from start date to end date
    @Override
    public String getDateString() {
        return startDate.getDateString() + " - " + endDate.getDateString();
    }

    // MODIFIES: this
    // EFFECTS: adds daily itinerary to list of daily itineraries with its date serving as the key;
    //          added daily itinerary gets updated so that it knows it belongs to the overview itinerary
    //          to which it was added to
    public void addDailyItinerary(DailyItinerary dailyItinerary) {
        if (dailyItinerary.getDate().getDate() != null && !dailyItineraries.containsValue(dailyItinerary)) {
            dailyItineraries.put(dailyItinerary.getDate(), dailyItinerary);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new date key with null value, if key already exists, do nothing
    public void addTripDateKey(TripDate td) {
        if (!dailyItineraries.containsKey(td)) {
            dailyItineraries.put(td, null);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates keys for all dates in between the start date of the trip and the end date
    public void createDatesBetween() {
        for (TripDate td = startDate; !td.getDate().equals(endDate.getDate()); td = td.getNextDate()) {
            addTripDateKey(td);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a daily itinerary for all dates that don't already have a daily itinerary
    public void createDailyItinerariesBetween() {
        for (TripDate td : dailyItineraries.keySet()) {
            if (dailyItineraries.get(td) == null) {
                dailyItineraries.put(td, createDailyItineraryUsingDate(td));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a daily itinerary whose date matches the date given
    public DailyItinerary createDailyItineraryUsingDate(TripDate td) {
        return new DailyItinerary(tripName, td);
    }


    // EFFECTS: represents overview itinerary objects using their trip name
    @Override
    public String toString() {
        return tripName;
    }

}
