package ui;

import model.DailyItinerary;
import model.OverviewItinerary;
import model.TripDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OverviewItineraryPanel extends Panel {

    OverviewItinerary overviewItinerary;
    DailyItineraryHolderPanel holderPanel;
    OptionPanels optionPanels;
    JTextField countryName;

    public OverviewItineraryPanel(OverviewItinerary oi, OptionPanels optionPanels) {
        holderPanel = new DailyItineraryHolderPanel();
        overviewItinerary = oi;
        this.optionPanels = optionPanels;

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, 0)));

        this.add(setupOverviewItineraryPanel());

        this.add(Box.createRigidArea(new Dimension(30, 0)));

        JScrollPane scrollPane = new JScrollPane(holderPanel.getPanel());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane);
    }

    public Panel setupOverviewItineraryPanel() {
        Panel panel = new Panel();
        setupComponents(panel);

        return panel;
    }

    private void setupComponents(Panel panel) {
        createLabels(panel);

        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        setupDailyItineraryOverviewPanels(panel);

        panel.add(Box.createRigidArea(new Dimension(0, 35)));

        optionPanels.createHomeButton(panel);
    }


    private void createLabels(Panel panel) {
        JLabel tripName = setupLabel(overviewItinerary.getTripName());
        JLabel date = setupSubLabel(overviewItinerary.getDateString());

        panel.add(tripName);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(date);
        panel.add(Box.createRigidArea(new Dimension(0, 7)));
        setLocationCountry(panel);
    }

    public void setupDailyItineraryOverviewPanels(Panel p) {
        for (TripDate date : overviewItinerary.getTripDates()) {
            createDailyItineraryOverviewPanel(p, overviewItinerary.getDailyItineraries().get(date));
        }
    }

    public void createDailyItineraryOverviewPanel(Panel oip, DailyItinerary dailyItinerary) {
        DailyItineraryOverview dailyItineraryOverview = new DailyItineraryOverview(dailyItinerary, holderPanel);

        JButton dailyOverview = dailyItineraryOverview.createDailyOverviewButton();
        dailyOverview.setAlignmentX(Component.CENTER_ALIGNMENT);

        oip.add(dailyOverview);
    }

    public void setLocationCountry(Panel panel) {
        if (overviewItinerary.getLocationCountry().equals("")) {
            newCountry(panel);
        } else {
            panel.add(setupText(overviewItinerary.getLocationCountry()));
        }
    }

    private void newCountry(Panel panel) {
        Panel country = new Panel();
        country.setLayout(new BoxLayout(country, BoxLayout.X_AXIS));
        country.add(setupText("Location"));
        country.add(Box.createRigidArea(new Dimension(0, 5)));
        JTextField countrySet = new JTextField(15);
        countrySet.setPreferredSize(new Dimension(10, 20));
        countrySet.setMaximumSize(countrySet.getPreferredSize());
        countrySet.setAlignmentX(Component.CENTER_ALIGNMENT);
        countryName = countrySet;
        country.add(countrySet);
        countrySet.addActionListener(countryActionListener(panel));
        panel.add(Box.createRigidArea(new Dimension(10, 0)));

        panel.add(country);

    }

    private ActionListener countryActionListener(final Panel panel) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                overviewItinerary.setLocationCountry(countryName.getText());
                refresh(panel);
            }
        };

        return actionListener;
    }

    private void refresh(Panel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        setupComponents(panel);
    }
}
