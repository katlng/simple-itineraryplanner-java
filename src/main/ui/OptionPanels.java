package ui;

import model.OverviewItinerary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class OptionPanels {

    private TripDirectory tripDirectory;

    private JPanel cards;
    private CardLayout cardLayout = new CardLayout();

    private HashMap<String, Panel> panels;

    public OptionPanels(TripDirectory td) {
        tripDirectory = td;
        panels = new HashMap<>();
        setPanels();
        setCards();
        cardLayout.show(cards, "Home");
    }

    // MODIFIES: this
    // EFFECTS: sets trip directory
    public void setTripDirectory(TripDirectory td) {
        if (tripDirectory == null || !tripDirectory.equals(td)) {
            tripDirectory = td;
        }
    }

    // EFFECTS: returns panels
    public HashMap<String, Panel> getPanels() {
        return panels;
    }

    // EFFECTS: returns cards
    public JPanel getCards() {
        return cards;
    }

    // EFFECTS: returns cardlayout
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    // MODIFIES: this
    // EFFECTS: sets up the three starting panels
    public void setPanels() {
        panels.put("Home", homePanel());
        panels.put("View trips", viewTripsPanel());
        panels.put("Create trip", newTripPanel());
    }

    // MODIFIES: this
    // EFFECTS: adds panels to cards, the panel that holds the rest of the panels
    public void setCards() {
        cards = new JPanel(cardLayout);
        for (String s : panels.keySet()) {
            cards.add(panels.get(s), s);
        }
    }

    // EFFECTS: creates a panel for the starting options
    public Panel homePanel() {
        Panel panel = new Panel();

        panel.add(Box.createRigidArea(new Dimension(0, 80)));
        JLabel welcomeLabel = panel.setupLabel("Welcome");
        panel.add(welcomeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 59)));

        setupHomePanel(panel);

        return panel;
    }

    // EFFECTS: creates a panel for the select a trip option
    public Panel viewTripsPanel() {
        Panel panel = new Panel();

        setupViewTripPanel(panel);

        return panel;
    }

    public void setupViewTripPanel(Panel panel) {
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        JLabel welcomeLabel = panel.setupLabel("Select a trip");
        panel.add(welcomeLabel);
        setupViewTripComboBox(panel);
        createHomeButton(panel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
    }

    // EFFECTS: creates a panel for the create a new trip options
    public Panel newTripPanel() {
        NewTripPanel newTripPanel = new NewTripPanel(tripDirectory, this);
        Panel panel = newTripPanel.createNewTripPanel();

        return panel;
    }

    // MODIFIES: home
    // EFFECTS: adds buttons to the given panel
    public void setupHomePanel(Panel home) {
        JButton createTrip = home.setupButton("Create a new trip");
        JButton viewTrip = home.setupButton("View existing trips");

        createTripActionListener(createTrip);

        viewTripActionListener(viewTrip);

        home.add(createTrip);
        home.add(Box.createRigidArea(new Dimension(0, 20)));
        home.add(viewTrip);
    }

    private void createTripActionListener(JButton button) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Create trip");
            }
        };

        button.addActionListener(actionListener);
    }

    private void viewTripActionListener(JButton button) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "View trips");
            }
        };
        button.addActionListener(actionListener);
    }

    public void setupViewTripComboBox(Panel panel) {
        ArrayList<String> tripNames = tripDirectory.tripNames();
        JComboBox trips = new JComboBox<>(tripNames.toArray());
        comboBoxActionListener(trips);

        panel.add(trips);
    }

    private void comboBoxActionListener(JComboBox box) {
        final JComboBox comboBox = box;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OverviewItinerary oi =
                        tripDirectory.accessItineraryByName((String) comboBox.getSelectedItem());
                if (oi == null) {
                    createNoTripFoundMessage();
                } else {
                    createOverviewItineraryPanel(oi);
                    cardLayout.show(cards, (String) comboBox.getSelectedItem());
                }
            }
        };
        comboBox.addActionListener(actionListener);
    }

    public void createOverviewItineraryPanel(OverviewItinerary oi) {
        OverviewItineraryPanel oip;
        if (!panels.keySet().contains(oi.getTripName())) {
            oip = new OverviewItineraryPanel(oi, this);
            cards.add(oip, oi.getTripName());
            panels.put(oi.getTripName(), oip);
        }
    }

    private void createNoTripFoundMessage() {
        JOptionPane.showMessageDialog(null, "Not a valid trip", "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void createHomeButton(Panel panel) {
        ImageIcon home = new ImageIcon("data/Home-Black-Icon-PNG-715x715.png");
        Image homeImage = home.getImage();
        Image scaledHome = homeImage.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        home = new ImageIcon(scaledHome);
        JButton button = new JButton(home);

        button.setBorder(null);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Home");
            }
        });

        panel.add(button);

    }
}
