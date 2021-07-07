package ui;

import model.Itineraries;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel extends JPanel {

    protected Itineraries itineraries;

    public Panel() {
        setup();
    }

    // EFFECTS: returns trip directory
    public Itineraries getItineraries() {
        return itineraries;
    }

    // MODIFIES: this
    // EFFECTS: sets trip directory with given trip directory
    public void setItineraries(Itineraries i) {
        if (!i.equals(itineraries)) {
            itineraries = i;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the layout for the panel
    public void setup() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(TripDirectory.BACKGROUND_COLOUR);
    }

    // EFFECTS: creates new label with given text
    public JLabel setupLabel(String text) {
        JLabel label = new JLabel(text);

        label.setFont(TripDirectory.LABEL_FONT);
        label.setForeground(TripDirectory.LABEL_FONT_COLOUR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }

    public JLabel setupSubLabel(String text) {
        JLabel label = new JLabel(text);

        label.setFont(TripDirectory.SUB_LABEL_FONT);
        label.setForeground(TripDirectory.SUB_LABEL_FONT_COLOUR);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }

    // EFFECTS: sets up buttons
    public JButton setupButton(String text) {
        JButton button = new JButton(text);

        button.setBackground(Color.BLACK);
        button.setForeground(TripDirectory.BODY_FONT_COLOUR);
        button.setFont(TripDirectory.BODY_FONT);
        button.setBorderPainted(true);
        button.setFocusPainted(false);

        button.setContentAreaFilled(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        return button;
    }

    public JLabel setupText(String text) {
        JLabel bodyText = new JLabel(text);

        bodyText.setFont(TripDirectory.BODY_FONT);
        bodyText.setForeground(TripDirectory.BODY_FONT_COLOUR);
        bodyText.setAlignmentX(Component.CENTER_ALIGNMENT);

        return bodyText;
    }

    public Border setupBorderForPanel() {
        Border border = BorderFactory.createLineBorder(TripDirectory.BORDER_COLOUR, 3);
        return border;
    }

}
