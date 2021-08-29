package frontend.controller;

import backend.datenbasis.Speicher;
import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import frontend.MainGUI;
import frontend.UIData.EventUI;
import frontend.UIData.TeilEventUI;

import javax.swing.*;
import java.awt.*;

public class MainGUIController extends GUIController {
    private MainGUI mainGUI;
    private Speicher speicher;


    public MainGUIController(MainGUI mainGUI, Speicher speicher) {
        this.mainGUI = mainGUI;
        this.speicher = speicher;
    }


    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd().equals(EventListGUIController.Commands.OPEN_EVENTPANE)) {
            JFrame frame = new JFrame();
            frame.add(mainGUI.getEventpane());
            mainGUI.getEventDetailsController().getUebersicht().getAttributeComponent().clearValues();
            mainGUI.getEventDetailsController().getUebersicht().getKontaktBeschreibungComponent().clearValues();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }
        else if (ge.getCmd().equals(EventDetailsController.Commands.CREATE_TEILEVENT)) {
            JFrame frame = new JFrame();
            frame.add(mainGUI.getTeileventspane());
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getAttributeComponent().clearValues();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getKontaktInformation().clearValues();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getSpecialComponent().clearValues();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().setTeilEventIdent(ge.getData());
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(800, 600));
        }
        else if (ge.getCmd().equals(EventListGUIController.Commands.OPEN_EDIT_EVENTPANE)) {
            JFrame frame = new JFrame();
            EventUI event = (EventUI) ge.getData();
            frame.add(mainGUI.getEventpane());
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getAttributeComponent().clearValues();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getKontaktInformation().clearValues();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().getSpecialComponent().clearValues();
            mainGUI.getEventDetailsController().getEvent(event);
            mainGUI.getTeilEventDetailsController().loadElements();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }
        else if (ge.getCmd().equals(EventDetailsController.Commands.OPEN_EDIT_TEILEVENTPANE)) {
            JFrame frame = new JFrame();
            frame.add(mainGUI.getTeileventspane());
            TeilEventUI teilEventUI = (TeilEventUI) ge.getData();
            mainGUI.getTeilEventDetailsController().setCurrentTeilEventUI(teilEventUI);
            mainGUI.getTeilEventDetailsController().loadElements();
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsUebersicht().fillAttributes(teilEventUI);
            mainGUI.getTeilEventDetailsController().getTeilEventDetailsMitarbeiter().setCurrentTeilEventUI(teilEventUI);
            mainGUI.getTeilEventDetailsController().loadMitarbeiter();
            mainGUI.getTeilEventDetailsController().loadHilfsmittel();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }
        else if (ge.getCmd().equals(EventDetailsController.Commands.RELOAD_PAGE) || ge.getCmd().equals(EventListGUIController.Commands.RELOAD_PAGE)) {
            mainGUI.getEventListGUIController().loadElements();
            mainGUI.getTeilEventDetailsController().loadElements();
            mainGUI.getTeilEventDetailsController().loadMitarbeiter();
            mainGUI.getTeilEventDetailsController().loadHilfsmittel();
            mainGUI.getTeilEventDetailsController().loadAllHilfsmittel();
        }
        else if (ge.getCmd().equals(TeilEventDetailsController.Commands.ADD_MITARBEITER)) {
            JFrame frame = new JFrame();
            frame.add(mainGUI.getMitarbeiterDetails());
            TeilEventUI teilEventUI = (TeilEventUI) ge.getData();
            mainGUI.getMitarbeiterDetails().setCurrentTeilEventUI(teilEventUI);
            mainGUI.getTeilEventDetailsController().loadAllMitarbeiter();
            frame.setVisible(true);frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }
        else if (ge.getCmd().equals(TeilEventDetailsController.Commands.ADD_HILFSMITTEL)) {
            JFrame frame = new JFrame();
            frame.add(mainGUI.getHilfsmittelDetails());
            TeilEventUI teilEventUI = (TeilEventUI) ge.getData();
            mainGUI.getHilfsmittelDetails().setCurrentTeilEventUI(teilEventUI);
            mainGUI.getTeilEventDetailsController().loadAllHilfsmittel();
            frame.setVisible(true);frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
        }
    }


    public enum Commands implements EventCommand {
        ;
        private String cmdText;
        private Class<?> payloadType;
        private Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }


        @Override
        public String getCmdText() {
            return this.cmdText;
        }

        @Override public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }


    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public Speicher getSpeicher() {
        return speicher;
    }

    public void setSpeicher(Speicher speicher) {
        this.speicher = speicher;
    }
}