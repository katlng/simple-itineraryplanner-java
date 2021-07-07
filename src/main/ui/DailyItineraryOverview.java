package ui;

import model.DailyItinerary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DailyItineraryOverview {

    DailyItinerary dailyItinerary;
    Panel panel;
    DailyItineraryHolderPanel holderPanel;

    public DailyItineraryOverview(DailyItinerary di, DailyItineraryHolderPanel holderPanel) {
        dailyItinerary = di;
        panel = new Panel();
        this.holderPanel = holderPanel;
    }

    // EFFECTS: returns daily itinerary
    public DailyItinerary getDailyItinerary() {
        return dailyItinerary;
    }

    public JButton createDailyOverviewButton() {
        JButton dailyOverviewButton = panel.setupButton(dailyItinerary.getDateString());
        dailyOverviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                holderPanel.addDailyItineraryPanel(new DailyItineraryPanel(dailyItinerary));
                holderPanel.showPanel(dailyItinerary.getDateString());
            }
        });

        return dailyOverviewButton;
    }
}
