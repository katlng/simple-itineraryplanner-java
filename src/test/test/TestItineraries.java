package test;

import model.Itineraries;
import model.OverviewItinerary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class TestItineraries {

    Itineraries i;

    @BeforeEach
    public void setup() {
        i = new Itineraries();
    }

    @Test
    public void testConstructor() {
        Itineraries i1 = new Itineraries();
        assertTrue(i1.getItineraries() != null);
    }

    @Test
    public void testGetItineraries() {
        assertTrue(i.getItineraries().size() == 0);
        assertTrue(i.getItineraries() != null);

        OverviewItinerary oi = new OverviewItinerary("as", "07/14/2018", "07/15/2018");
        i.getItineraries().add(oi);

        assertTrue(i.getItineraries().size() == 1);
    }
}
