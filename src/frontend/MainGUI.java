package frontend;

import backend.datenbasis.Speicher;
import backend.datenbasis.factory.HilfsmittelFactory;
import frontend.controller.*;
import frontend.pages.*;
import frontend.panes.EventDetailsPane;
import frontend.panes.LagerDetailsPane;
import frontend.panes.OverviewPane;
import frontend.panes.TeilEventDetailsPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainGUI extends JComponent {

    private JFrame frame;
    private EventDetailsPane eventpane;
    private LagerDetailsPane lagerpane;
    private OverviewPane overviewpane;
    private TeilEventDetailsPane teileventspane;
    private MainGUIController mainGUIController;
    private Speicher speicher;

    public JFrame getFrame() {
        return frame;
    }

    public EventDetailsPane getEventpane() {
        return eventpane;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }

    public LagerDetailsPane getLagerpane() {
        return lagerpane;
    }

    public OverviewPane getOverviewpane() {
        return overviewpane;
    }

    public TeilEventDetailsPane getTeileventspane() {
        return teileventspane;
    }

    public MainGUIController getMainGUIController() {
        return mainGUIController;
    }

    public MainGUI() {

        speicher = new Speicher();
        speicher.load();

        frame = new JFrame();
        frame.setSize(1280, 960);
        frame.setTitle("EventPlaner");
        frame.getContentPane().setLayout(new GridLayout(1, 1));

        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Wollen Sie die Anwendung schließen?",
                        "Beenden", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    speicher.save();
                    System.exit(0);
                }
            }
        };
        frame.addWindowListener(exitListener);

        mainGUIController = new MainGUIController(this, this.speicher);

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

        EventDetailsController eventDetailsController = new EventDetailsController(eventDetailsÜbersicht, eventDetailsTeilevent, eventDetailsKosten, mainGUIController, this.speicher);
        EventListGUIController eventListGUIController = new EventListGUIController(eventListGUI, mainGUIController, this.speicher);
        HilfsmittelDetailsController hilfsmittelDetailsController = new HilfsmittelDetailsController(hilfsmittelDetails, this.speicher);
        KalenderGUIConroller kalenderGUIConroller = new KalenderGUIConroller(kalenderGUI);
        LagerListGUIController lagerListGUIController = new LagerListGUIController(lagerListGUI, this.speicher);
        TeilEventDetailsController teilEventDetailsController = new TeilEventDetailsController(teilEventDetailsÜbersicht, teilEventDetailsMitarbeiter, teilEventDetailsHilfsmittel, this.speicher);

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





        frame.add(overviewpane);

        //frame.add(eventpane);
        //frame.add(teileventspane);


        frame.setVisible(true);

    }
}
