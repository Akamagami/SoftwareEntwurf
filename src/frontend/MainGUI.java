package frontend;

import frontend.pages.*;
import frontend.panes.EventDetailsPane;
import frontend.panes.LagerDetailsPane;
import frontend.panes.OverviewPane;
import frontend.panes.TeilEventDetailsPane;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JComponent {

    private JFrame frame;
    private JTabbedPane jTabbedPane;
    private EventDetailsPane eventpane;
    private LagerDetailsPane lagerpane;
    private OverviewPane overviewpane;
    private TeilEventDetailsPane teileventspane;

    public MainGUI() {
        frame = new JFrame();
        frame.setSize(1280, 960);
        frame.setTitle("EventPlaner");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(1, 1));

        JTabbedPane jTabbedPane = new JTabbedPane(1);


        eventpane = new EventDetailsPane();
        lagerpane = new LagerDetailsPane();
        overviewpane = new OverviewPane();
        teileventspane = new TeilEventDetailsPane();

        EventDetailsÜbersicht übersicht = new EventDetailsÜbersicht();
        EventDetailsTeilevent teilevent = new EventDetailsTeilevent();
        EventDetailsKosten kosten = new EventDetailsKosten();
        eventpane.addTab("Übersicht", übersicht);
        eventpane.addTab("Teilevents", teilevent);
        eventpane.addTab("Kosten", kosten);

        HilfsmittelDetails hilfsmittelDetails = new HilfsmittelDetails();
        lagerpane.addTab("Hilfsmittel", hilfsmittelDetails);

        KalenderGUI kalenderGUI = new KalenderGUI();
        EventListGUI eventListGUI = new EventListGUI();
        LagerListGUI lagerListGUI = new LagerListGUI();
        overviewpane.addTab("Events", eventListGUI);
        overviewpane.addTab("Lager", lagerListGUI);
        overviewpane.addTab("Kalender", kalenderGUI);

        TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter = new TeilEventDetailsMitarbeiter();
        TeilEventDetailsÜbersicht teilEventDetailsÜbersicht = new TeilEventDetailsÜbersicht();
        TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel = new TeilEventDetailsHilfsmittel();
        teileventspane.addTab("Übersicht", teilEventDetailsÜbersicht);
        teileventspane.addTab("Mitarbeiter", teilEventDetailsMitarbeiter);
        teileventspane.addTab("Hilfsmittel", teilEventDetailsHilfsmittel);


        jTabbedPane.addTab("Übersicht", overviewpane);
        jTabbedPane.addTab("EventDetails", eventpane);
        jTabbedPane.addTab("TeileventDetails", teileventspane);
        jTabbedPane.addTab("Lager", lagerpane);


        frame.add(jTabbedPane);

        frame.setVisible(true);

    }
}
