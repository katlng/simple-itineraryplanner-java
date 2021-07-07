package model;

public abstract class Itinerary {

    protected String tripName;
    protected String locationCountry;

    // EFFECTS: sets name of trip and location country
    protected Itinerary(String tripName) {
        this.tripName = tripName;
        locationCountry = "";
    }

    // MODIFIES: this
    // EFFECTS: sets new trip name
    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    // MODIFIES: this
    // EFFECTS: sets the country in which the trip takes place
    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }

    // EFFECTS: returns name of trip
    public String getTripName() {
        return tripName;
    }

    // EFFECTS: returns country location of trip
    public String getLocationCountry() {
        return locationCountry;
    }

    public abstract String getDateString();

    // EFFECTS: creates a new tripDate with the given date
    public TripDate createDate(String date) {
        return new TripDate(date);
    }
}
