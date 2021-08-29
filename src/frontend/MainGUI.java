package frontend;

import backend.datenbasis.Speicher;
import frontend.controller.*;
import frontend.pages.*;
import frontend.panes.EventDetailsPane;
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
    private OverviewPane overviewpane;
    private TeilEventDetailsPane teileventspane;
    private MainGUIController mainGUIController;
    private Speicher speicher;
    private EventDetailsController eventDetailsController;
    EventListGUIController eventListGUIController;
    TeilEventDetailsController teilEventDetailsController;


    public MainGUI() {

        speicher = new Speicher();
        speicher.load();
        speicher.testzuweisung();

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

        overviewpane = new OverviewPane();
        teileventspane = new TeilEventDetailsPane();

        EventDetailsUebersicht eventDetailsUebersicht = new EventDetailsUebersicht();
        EventDetailsTeilevent eventDetailsTeilevent = new EventDetailsTeilevent();
        eventpane.addTab("Uebersicht", eventDetailsUebersicht);
        eventpane.addTab("Teilevents", eventDetailsTeilevent);


        EventListGUI eventListGUI = new EventListGUI();
        overviewpane.addTab("Events", eventListGUI);

        TeilEventDetailsMitarbeiter teilEventDetailsMitarbeiter = new TeilEventDetailsMitarbeiter();
        TeilEventDetailsUebersicht teilEventDetailsUebersicht = new TeilEventDetailsUebersicht();
        TeilEventDetailsHilfsmittel teilEventDetailsHilfsmittel = new TeilEventDetailsHilfsmittel();
        teileventspane.addTab("Uebersicht", teilEventDetailsUebersicht);
        teileventspane.addTab("Mitarbeiter", teilEventDetailsMitarbeiter);
        teileventspane.addTab("Hilfsmittel", teilEventDetailsHilfsmittel);

        eventDetailsController = new EventDetailsController(eventDetailsUebersicht, eventDetailsTeilevent, mainGUIController, this.speicher);
        eventListGUIController = new EventListGUIController(eventListGUI, mainGUIController, this.speicher);
        teilEventDetailsController = new TeilEventDetailsController(teilEventDetailsUebersicht, teilEventDetailsMitarbeiter, teilEventDetailsHilfsmittel, this.speicher, mainGUIController);

        eventDetailsUebersicht.setController(eventDetailsController);
        eventDetailsTeilevent.setController(eventDetailsController);
        eventListGUI.setController(eventListGUIController);
        teilEventDetailsUebersicht.setController(teilEventDetailsController);
        teilEventDetailsMitarbeiter.setController(teilEventDetailsController);
        teilEventDetailsHilfsmittel.setController(teilEventDetailsController);





        frame.add(overviewpane);

        frame.setVisible(true);

    }

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

    public OverviewPane getOverviewpane() {
        return overviewpane;
    }

    public TeilEventDetailsPane getTeileventspane() {
        return teileventspane;
    }

    public MainGUIController getMainGUIController() {
        return mainGUIController;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setEventpane(EventDetailsPane eventpane) {
        this.eventpane = eventpane;
    }

    public void setOverviewpane(OverviewPane overviewpane) {
        this.overviewpane = overviewpane;
    }

    public void setTeileventspane(TeilEventDetailsPane teileventspane) {
        this.teileventspane = teileventspane;
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public EventDetailsController getEventDetailsController() {
        return eventDetailsController;
    }

    public void setEventDetailsController(EventDetailsController eventDetailsController) {
        this.eventDetailsController = eventDetailsController;
    }

    public EventListGUIController getEventListGUIController() {
        return eventListGUIController;
    }

    public void setEventListGUIController(EventListGUIController eventListGUIController) {
        this.eventListGUIController = eventListGUIController;
    }

    public TeilEventDetailsController getTeilEventDetailsController() {
        return teilEventDetailsController;
    }

    public void setTeilEventDetailsController(TeilEventDetailsController teilEventDetailsController) {
        this.teilEventDetailsController = teilEventDetailsController;
    }
}
