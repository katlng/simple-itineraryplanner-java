package ui;

import model.OverviewItinerary;
import model.TripDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewTripPanel {
    // month --> date --> year
    private ArrayList<JTextField> startDate;
    private ArrayList<JTextField> endDate;
    private JTextField nameOfTrip;

    TripDirectory tripDirectory;

    OptionPanels optionPanels;

    public NewTripPanel(TripDirectory tripDirectory, OptionPanels optionPanels) {
        this.tripDirectory = tripDirectory;
        startDate = new ArrayList<>();
        endDate = new ArrayList<>();
        this.optionPanels = optionPanels;
    }

    public Panel createNewTripPanel() {
        Panel panel = new Panel();

        setupPanelLabel(panel);
        JTextField name = setupNewTripName(panel);
        panel.add(name);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));

        setupStartDate(panel);

        setupEndDate(panel);

        JButton button = panel.setupButton("Submit");
        button.setAlignmentX(Box.CENTER_ALIGNMENT);
        submitButtonActionListener(button);
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        optionPanels.createHomeButton(panel);


        return panel;
    }

    private void setupPanelLabel(Panel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        JLabel newTripLabel = panel.setupLabel("Create a new trip");
        newTripLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(newTripLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
    }

    // MODIFIES: newTrip
    // EFFECTS: adds text field to the given panel, and returns text field created
    private JTextField setupNewTripName(Panel newTrip) {

        JLabel label = newTrip.setupSubLabel("What will your trip's name be?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        newTrip.add(label);
        newTrip.add(Box.createRigidArea(new Dimension(0, 20)));
        JTextField textField = new JTextField(30);
        textField.setPreferredSize(new Dimension(10, 20));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameOfTrip = textField;

        return textField;
    }

    private Panel setupDateChooser(int startOrEnd) {
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(setupDateTextField("Month", startOrEnd, 2));
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(setupDateTextField("Day", startOrEnd, 2));
        panel.add(Box.createRigidArea(new Dimension(15, 0)));
        panel.add(setupDateTextField("Year", startOrEnd, 4));

        return panel;
    }

    private Panel setupDateTextField(String time, int startOrEnd, int length) {
        Panel panel = new Panel();

        JLabel label = panel.setupText(time);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        JTextField textField = new JTextField(length);
        textField.setPreferredSize(new Dimension(20, 25));
        textField.setMaximumSize(textField.getPreferredSize());
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(textField);

        if (startOrEnd == 1) {
            startDate.add(textField);
        } else {
            endDate.add(textField);
        }

        return panel;
    }

    private void submitButtonActionListener(JButton button) {
        JButton submit = button;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameOfTrip.getText();
                if (checkIfTripNameExists(name)) {
                    TripDate startingDate = createDate(startDate);
                    TripDate endingDate = createDate(endDate);

                    tripDirectory.addOverviewItinerary(new OverviewItinerary(name, startingDate, endingDate));
                    refresh();

                } else {
                    JOptionPane.showMessageDialog(
                            null, "A trip with that name already exists",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void refresh() {
        JOptionPane.showMessageDialog(null, "Trip successfully created!",
                "Successful", JOptionPane.INFORMATION_MESSAGE);
        optionPanels.getCardLayout().show(optionPanels.getCards(), "Home");
        reloadViewTripPanel();
        refreshTextFields();
    }

    private void reloadViewTripPanel() {
        Panel viewTrip = optionPanels.getPanels().get("View trips");
        viewTrip.removeAll();
        viewTrip.revalidate();
        viewTrip.repaint();
        viewTrip.setup();

        optionPanels.setupViewTripPanel(viewTrip);
    }

    private void refreshTextFields() {
        nameOfTrip.setText("");
        for (JTextField tf : startDate) {
            tf.setText("");
        }
        for (JTextField tf : endDate) {
            tf.setText("");
        }
    }

    private TripDate createDate(ArrayList<JTextField> time) {
        String month = time.get(0).getText();
        String date = time.get(1).getText();
        String year = time.get(2).getText();

        TripDate day = new TripDate(month + "/" + date + "/" + year);

        return day;
    }

    private boolean checkIfTripNameExists(String trip) {
        for (OverviewItinerary oi : tripDirectory.getMyTrips()) {
            if (oi.getTripName().equals(trip)) {
                return false;
            }
        }
        return true;
    }

    private void setupStartDate(Panel panel) {
        JLabel label = panel.setupSubLabel("When will your trip start?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        setUpLayoutOfDates(1, panel);
    }

    private void setupEndDate(Panel panel) {
        JLabel label = panel.setupSubLabel("When will your trip end?");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        setUpLayoutOfDates(2, panel);
    }

    private void setUpLayoutOfDates(int i, Panel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(setupDateChooser(i));
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
    }

}
