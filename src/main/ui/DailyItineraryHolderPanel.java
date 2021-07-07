package ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class DailyItineraryHolderPanel {

    CardLayout cardLayout = new CardLayout();
    JPanel panel = new JPanel(cardLayout);

    Set<String> dailyItineraryPanels;

    public DailyItineraryHolderPanel() {

        dailyItineraryPanels = new HashSet<>();
        addStartingPanel();
        cardLayout.show(panel, "Starting");
    }

    // EFFECTS: returns panel with card layout
    public JPanel getPanel() {
        return panel;
    }

    public void showPanel(String time) {
        cardLayout.show(panel, time);
    }

    public void addStartingPanel() {
        Panel panel = new Panel();

        this.panel.add("Starting", panel);
    }

    public void addDailyItineraryPanel(DailyItineraryPanel dip) {
        if (!dailyItineraryPanels.contains(dip.getDailyItinerary().getDateString())) {
            panel.add(dip, dip.getDailyItinerary().getDateString());
            dailyItineraryPanels.add(dip.getDailyItinerary().getDateString());
        }
    }



}
