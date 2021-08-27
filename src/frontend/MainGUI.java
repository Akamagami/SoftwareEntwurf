package frontend;

import backend.datenbasis.factory.HilfsmittelFactory;
import frontend.controller.*;
import frontend.pages.*;
import frontend.panes.EventDetailsPane;
import frontend.panes.LagerDetailsPane;
import frontend.panes.OverviewPane;
import frontend.panes.TeilEventDetailsPane;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JComponent {

    private JFrame frame;
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


        eventpane = new EventDetailsPane();
        lagerpane = new LagerDetailsPane();
        overviewpane = new OverviewPane();
        teileventspane = new TeilEventDetailsPane();

        EventDetailsÜbersicht eventDetailsÜbersicht = new EventDetailsÜbersicht();
        EventDetailsTeilevent eventDetailsTeilevent = new EventDetailsTeilevent();
        EventDetailsKosten eventDetailsKosten = new EventDetailsKosten();
        eventpane.addTab("Übersicht", eventDetailsÜbersicht);
        eventpane.addTab("Teilevents", eventDetailsTeilevent);
        eventpane.addTab("Kosten", eventDetailsKosten);

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

        EventDetailsController eventDetailsController = new EventDetailsController(eventDetailsÜbersicht, eventDetailsTeilevent, eventDetailsKosten);
        EventListGUIController eventListGUIController = new EventListGUIController(eventListGUI);
        HilfsmittelDetailsController hilfsmittelDetailsController = new HilfsmittelDetailsController(hilfsmittelDetails);
        KalenderGUIConroller kalenderGUIConroller = new KalenderGUIConroller(kalenderGUI);
        LagerListGUIController lagerListGUIController = new LagerListGUIController(lagerListGUI);
        TeilEventDetailsController teilEventDetailsController = new TeilEventDetailsController(teilEventDetailsÜbersicht, teilEventDetailsMitarbeiter, teilEventDetailsHilfsmittel);

        eventDetailsÜbersicht.setController(eventDetailsController);
        eventDetailsTeilevent.setController(eventDetailsController);
        eventDetailsKosten.setController(eventDetailsController);
        eventListGUI.setController(eventListGUIController);
        hilfsmittelDetails.setController(hilfsmittelDetailsController);
        kalenderGUI.setController(kalenderGUIConroller);
        lagerListGUI.setController(lagerListGUIController);
        teilEventDetailsÜbersicht.setController(teilEventDetailsController);
        teilEventDetailsMitarbeiter.setController(teilEventDetailsController);
        teilEventDetailsHilfsmittel.setController(teilEventDetailsController);





        //frame.add(overviewpane);

        frame.add(lagerpane);

        frame.setVisible(true);

    }
}
