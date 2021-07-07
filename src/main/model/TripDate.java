package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class TripDate implements Comparable<TripDate> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private Date date;

    // EFFECTS: creates new trip date using a string
    public TripDate(String date) {
        DATE_FORMAT.setLenient(false);
        try {
            this.date = DATE_FORMAT.parse(date);
        } catch (Exception e) {
            System.out.println("Not valid date");
        }
    }

    // EFFECTS: returns date
    public Date getDate() {
        return date;
    }

    // EFFECTS: returns date
    public String getDateString() {
        return DATE_FORMAT.format(date);
    }

    // EFFECTS: returns a trip date that has tomorrow's date given the tripDate's date
    //          that the method is called on
    public TripDate getNextDate() {
        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.add(Calendar.DAY_OF_YEAR, 1);
        TripDate tomorrow;
        tomorrow = new TripDate(DATE_FORMAT.format(today.getTime()));
        return tomorrow;

    }

    // EFFECTS: compares two trip dates based on their dates, and which one is earlier or later
    public int compareTo(TripDate td) {
        return getDate().compareTo(td.getDate());
    }


    // EFFECTS: checks if two trip dates are equal or not;
    //          two trip dates are equal if they have the same date
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TripDate tripDate = (TripDate) o;
        return Objects.equals(date, tripDate.date);
    }

    // EFFECTS: generates a hashcode for a tripdate object depending on its date
    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
