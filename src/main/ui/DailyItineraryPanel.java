package ui;

import model.DailyItinerary;
import model.TripEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DailyItineraryPanel extends Panel {

    private DailyItinerary dailyItinerary;
    // 0: activity
    // 1: address
    // 2: hour
    // 3: minutes
    private ArrayList<JTextField> newEventDetails;

    public DailyItineraryPanel(DailyItinerary dailyItinerary) {
        super();
        this.dailyItinerary = dailyItinerary;
        newEventDetails = new ArrayList<>();

        setupDailyItineraryPanel(this);

        createTimeLines(this);
    }

    // EFFECTS: returns daily itinerary associated with the panel
    public DailyItinerary getDailyItinerary() {
        return dailyItinerary;
    }

    // MODIFIES: this
    // EFFECTS: sets daily itinerary with given daily itinerary
    public void setDailyItinerary(DailyItinerary dailyItinerary) {
        if (!dailyItinerary.equals(this.dailyItinerary)) {
            this.dailyItinerary = dailyItinerary;
        }
    }

    public void setupDailyItineraryPanel(Panel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 18)));
        JLabel dateLabel = setupLabel(dailyItinerary.getDateString());
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(dateLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        createNewEventPanel(this);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
    }

    public void createTimeLines(Panel panel) {
        for (int i = 0; i < 24; i++) {
            JLabel timeLabel = setupSubLabel(i + ":00");
            timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            timeLabel.setForeground(Color.lightGray);
            panel.add(timeLabel);
            for (TripEvent te : checkEventTime(i)) {
                panel.add(new TripEventPanel(te));
            }
        }
    }

    public ArrayList<TripEvent> checkEventTime(int i) {
        ArrayList<TripEvent> tripEvents = new ArrayList<>();
        Date hour = instantiateDate(i);
        Date oneHourAfter = instantiateDate(++i);
        for (TripEvent event : dailyItinerary.getEventManager().getEvents()) {
            if (event.getTime().after(hour) && event.getTime().before(oneHourAfter)) {
                tripEvents.add(event);
            }
        }
        return tripEvents;
    }

    public Date instantiateDate(int i) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date hour = null;
        if (i < 10) {
            try {
                hour = dateFormat.parse("0" + i + ":00");
            } catch (ParseException pe) {
                printNotValidDate();
            }
        } else {
            try {
                hour = dateFormat.parse(i + ":00");
            } catch (ParseException pe) {
                printNotValidDate();
            }
        }
        return hour;
    }

    // EFFECTS: prints out the phrase "not a valid date"
    private static void printNotValidDate() {
        System.out.println("Not a valid date");
    }

    public void createPlusButton(Panel panel) {
        ImageIcon plus = new ImageIcon("data/25304.png");
        Image img = plus.getImage();
        Image scaledImg = img.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        plus = new ImageIcon(scaledImg);

        JButton plusButton = new JButton(plus);

        plusButton.setBorder(null);

        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, createNewEvent(),
                        "New Event", JOptionPane.PLAIN_MESSAGE);
            }
        });

        panel.add(plusButton);
    }

    public void createNewEventPanel(Panel panel) {
        Panel newEvent = new Panel();
        newEvent.setLayout(new BoxLayout(newEvent, BoxLayout.X_AXIS));

        createPlusButton(newEvent);
        newEvent.add(Box.createRigidArea(new Dimension(5, 0)));
        JLabel label = setupText("Add an event");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Apple Casual", Font.ITALIC, 9));
        newEvent.add(label);

        panel.add(newEvent);
    }

    public Panel createNewEvent() {
        Panel options = new Panel();

        setupNewEventLabel(options);
        options.add(Box.createRigidArea(new Dimension(0, 10)));

        activityNewEvent(options);
        addressNewEvent(options);

        JLabel time = setupText("What time will your event take place? (24-HOUR FORMAT)");
        options.add(time);
        options.add(Box.createRigidArea(new Dimension(0, 10)));
        setupTime(options);
        options.add(Box.createRigidArea(new Dimension(0, 17)));

        JButton button = setupButton("Submit");
        actionListenerNewEvent(button);
        options.add(button);

        return options;
    }

    private void activityNewEvent(Panel panel) {
        JTextField activity = new JTextField(40);
        activity.setPreferredSize(new Dimension(10, 20));
        activity.setMaximumSize(activity.getPreferredSize());

        activity.setAlignmentX(Component.CENTER_ALIGNMENT);

        newEventDetails.add(activity);

        JLabel label = setupText("Choose an activity");

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(activity);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void addressNewEvent(Panel panel) {
        JTextField address = new JTextField(40);
        address.setPreferredSize(new Dimension(10, 20));
        address.setMaximumSize(address.getPreferredSize());

        address.setAlignmentX(Component.CENTER_ALIGNMENT);

        newEventDetails.add(address);

        JLabel label = setupText("Where will you be?");

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(address);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    private void setupNewEventLabel(Panel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 12)));
        JLabel label = setupSubLabel("Create a new event");
        panel.add(label);
    }

    private void setupTime(Panel panel) {
        Panel time = setupTimeFields();
        panel.add(time);
    }

    private Panel setupTimeFields() {
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JTextField hour = setupTimeTextField();
        hour.setAlignmentX(Component.CENTER_ALIGNMENT);
        newEventDetails.add(hour);
        panel.add(hour);
        JLabel colon = setupSubLabel(":");
        panel.add(colon);
        JTextField minutes = setupTimeTextField();
        minutes.setAlignmentX(Component.CENTER_ALIGNMENT);
        newEventDetails.add(minutes);
        panel.add(minutes);

        return panel;
    }

    private JTextField setupTimeTextField() {
        JTextField time = new JTextField(2);
        time.setPreferredSize(new Dimension(10, 20));
        time.setMaximumSize(time.getPreferredSize());

        return time;
    }

    public void actionListenerNewEvent(JButton button) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String activity = newEventDetails.get(0).getText();
                String address = newEventDetails.get(1).getText();
                String time = createTime();
                for (JTextField textField : newEventDetails) {
                    textField.setText("");
                }
                newEventDetails.clear();
                dailyItinerary.addTripEvent(activity, time, address);
                refresh();
            }
        };
        button.addActionListener(actionListener);
    }

    private String createTime() {
        String hour = newEventDetails.get(2).getText();
        String minute = newEventDetails.get(3).getText();
        String hourMinutes = hour + ":" + minute;

        return hourMinutes;
    }

    public void refresh() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.setup();
        setupDailyItineraryPanel(this);
        createTimeLines(this);
    }
}

