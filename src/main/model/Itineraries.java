package model;

import java.util.ArrayList;

public class Itineraries {

    private ArrayList<OverviewItinerary> itineraries;

    // EFFECTS: instantiates itineraries field
    public Itineraries() {
        itineraries = new ArrayList<>();
    }

    // EFFECTS: returns list of itineraries
    public ArrayList<OverviewItinerary> getItineraries() {
        return itineraries;
    }

}
