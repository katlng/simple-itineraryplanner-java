package ui;

import model.TripEvent;

import javax.swing.*;
import java.awt.*;

public class TripEventPanel extends Panel {

    private static final Font LABEL_FONT = new Font("AppleGothic", Font.PLAIN, 8);
    private static final Font BODY_FONT = new Font("AppleGothic", Font.PLAIN, 5);

    TripEvent tripEvent;

    public TripEventPanel(TripEvent tripEvent) {
        super();
        this.tripEvent = tripEvent;

        setupTripEventPanel();

        JLabel address = setupText(tripEvent.getAddress());
        address.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(address);

    }

    @Override
    public JLabel setupLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL_FONT);
        label.setForeground(TripDirectory.SUB_LABEL_FONT_COLOUR);

        return label;
    }

    @Override
    public JLabel setupSubLabel(String text) {
        JLabel subLabel = new JLabel(text);
        subLabel.setForeground(TripDirectory.BODY_FONT_COLOUR);
        subLabel.setFont(BODY_FONT);

        return subLabel;
    }

    public void setupTripEventPanel() {
        JLabel label = setupText(tripEvent.getTimeString24() + " - " + tripEvent.getActivity());
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(label);
    }

    public Panel tripEventDetailsPanel() {
        Panel panel = new Panel();

        Panel image = new Panel();
//        JButton button = new JButton(tripEvent.getUrlManager().getMap());
//        button.setBorder(null);
//        image.add(button);


        Panel info = new Panel();

        info.add((panel.setupText(tripEvent.getTimeString24())));
        info.add(panel.setupText(tripEvent.getActivity()));
        info.add(panel.setupText(tripEvent.getAddress()));

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(image);
        panel.add(info);

        return panel;


    }
}
