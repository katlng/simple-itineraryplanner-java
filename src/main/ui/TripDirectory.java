package ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.Itineraries;
import model.OverviewItinerary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TripDirectory extends JFrame implements SaveLoad {

    public static void main(String[] args) {

        TripDirectory td = new TripDirectory();
        try {
            td.load(FILENAME);
        } catch (Exception e) {
            System.out.println("File not found");
        }
        td.createHome();
    }

    private static final String FILENAME = "log";

    public static final Font LABEL_FONT = new Font("AppleGothic", Font.PLAIN, 32);
    public static final Color LABEL_FONT_COLOUR = Color.lightGray;
    public static final Font SUB_LABEL_FONT = new Font("AppleGothic", Font.BOLD, 15);
    public static final Color SUB_LABEL_FONT_COLOUR = new Color(188,193,191);
    public static final Font BODY_FONT = new Font("Apple Symbols", Font.PLAIN, 15);
    public static final Color BODY_FONT_COLOUR = new Color(144, 153, 149);
    public static final Color BACKGROUND_COLOUR = Color.WHITE;
    public static final Color BORDER_COLOUR = Color.lightGray;

    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

    private Itineraries myTrips;
    private OptionPanels startingPanels;
    private Panel panel;

    public TripDirectory() {
        myTrips = new Itineraries();
        startingPanels = new OptionPanels(this);
        panel = new Panel();
    }

    //EFFECTS: returns array list of overview itineraries
    public ArrayList<OverviewItinerary> getMyTrips() {
        return myTrips.getItineraries();
    }

    // MODIFIES: this
    // EFFECTS: adds a new overview itinerary to the list of itineraries
    public void addOverviewItinerary(OverviewItinerary oi) {
        myTrips.getItineraries().add(oi);
    }

    public void initiateComponents() {
        panel.setItineraries(myTrips);
        startingPanels = new OptionPanels(this);
    }

    // EFFECTS: creates new window with starting options
    public void createHome() {
        initiateComponents();

        final JFrame displayFrame = setupDisplayFrame();

        JPanel cards = startingPanels.getCards();
        displayFrame.getContentPane().add(cards);

        displayFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    save(FILENAME);
                } catch (Exception exception) {
                    System.out.println("File not found");
                }

                System.exit(1);
                }

        });

        displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayFrame.setVisible(true);

    }

    // EFFECTS: sets up window's design
    public JFrame setupDisplayFrame() {
        JFrame displayFrame = new JFrame("Itinerary");

        displayFrame.setLayout(new BoxLayout(displayFrame.getContentPane(), BoxLayout.Y_AXIS));
        displayFrame.setFont(LABEL_FONT);
        displayFrame.setSize(new Dimension(WIDTH, HEIGHT));
        displayFrame.getContentPane().setForeground(BACKGROUND_COLOUR);
        displayFrame.getContentPane().setBackground(BACKGROUND_COLOUR);

        return displayFrame;
    }

    @Override
    public Gson setupGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.enableComplexMapKeySerialization();

        return gsonBuilder.setPrettyPrinting().create();
    }

    // MODIFIES: file
    // EFFECTS: saves a trips list of itineraries to the file with the
    //          given name
    @Override
    public void save(String filename) throws IOException {
        Gson gson = setupGson();

        String json = gson.toJson(myTrips);

        FileWriter writer = new FileWriter(filename);
        writer.write(json);
        writer.close();

    }

//     MODIFIES: this
//     EFFECTS: loads data saved to file with given filename, and saves it to
//              list of itineraries in myTrips
    @Override
    public void load(String filename) throws IOException {
        Gson gson = setupGson();

        JsonReader reader = new JsonReader(new FileReader(filename));
        Itineraries myTrips =  gson.fromJson(reader, Itineraries.class);

        this.myTrips = myTrips;
        checkMyTripsNull();
    }

    // MODIFIES: this
    // EFFECTS: instantiates myTrips if it's null
    public void checkMyTripsNull() {
        if (myTrips == null) {
            myTrips = new Itineraries();
        }
    }

    // EFFECTS: searches up itinerary with the same name as given name
    public OverviewItinerary accessItineraryByName(String name) {
        for (OverviewItinerary itinerary : myTrips.getItineraries()) {
            if (itinerary.getTripName().equals(name)) {
                return itinerary;
            }
        }
        return null;
    }

    // EFFECTS: returns array list of the trip names of all trips saved
    public ArrayList<String> tripNames() {
        ArrayList<String> tripNames = new ArrayList<>();
        tripNames.add("--Select a trip--");
        for (OverviewItinerary oi : getMyTrips()) {
            if (!tripNames.contains(oi.getTripName())) {
                tripNames.add(oi.getTripName());
            }
        }
        return tripNames;
    }


}
