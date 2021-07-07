package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class TripEvent implements Comparable<TripEvent> {

    private static final SimpleDateFormat TIME_FORMAT_12HR = new SimpleDateFormat("hh:mm a");
    private static final SimpleDateFormat TIME_FORMAT_24HR = new SimpleDateFormat("HH:mm");

    private String activity;
    private Date time;
    private String address;

    // EFFECTS: sets trip activity, time, and address
    public TripEvent(String activity, String time, String address) {
        this.activity = activity;
        try {
            this.time = TIME_FORMAT_24HR.parse(time);
        } catch (Exception e) {
            System.out.println("Not valid time");
        }
        this.address = address;

    }

    // MODIFIES: this
    // EFFECTS: sets activity with new activity
    public void setActivity(String activity) {
        this.activity = activity;
    }

    // MODIFIES: this
    // EFFECTS: sets old time with new time
    public void setTime(String time) {
        try {
            this.time = TIME_FORMAT_24HR.parse(time);
        } catch (Exception e) {
            System.out.println("Not valid time");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets address with new address given
    public void setAddress(String address) {
        this.address = address;
    }

    // EFFECTS: returns date object assigned to time
    public Date getTime() {
        return time;
    }

    // EFFECTS: returns time in a 12-hr format
    public String getTimeString12() {
        return TIME_FORMAT_12HR.format(time);
    }

    // EFFECTS: returns time in a 24-hr format
    public String getTimeString24() {
        return TIME_FORMAT_24HR.format(time);
    }

    // EFFECTS: returns the activity for an event
    public String getActivity() {
        return activity;
    }

    // EFFECTS: returns address for an event
    public String getAddress() {
        return address;
    }

    // EFFECTS: compares two trip events based on their time; whether one is earlier or later
    @Override
    public int compareTo(TripEvent te) {
        return getTime().compareTo(te.getTime());
    }

    // EFFECTS: checks if two trip events are equal or not based on their activity and address,
    //          they're equal if they have the same actiivty and address
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TripEvent tripEvent = (TripEvent) o;
        return Objects.equals(activity, tripEvent.activity)
                && Objects.equals(address, tripEvent.address);
    }

    // EFFECTS: creates a hashcode for a tripevent object based on their activity and address
    @Override
    public int hashCode() {

        return Objects.hash(activity, address);
    }
}
